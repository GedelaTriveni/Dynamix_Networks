package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.model.Booking;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HotelController {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    BookingRepository bookingRepo;

    // Home - show available rooms
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("rooms", roomRepo.findByStatus("Available"));
        return "index";
    }

    // Admin page
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("rooms", roomRepo.findAll());
        return "admin";
    }

    // Add room
    @PostMapping("/addRoom")
    public String addRoom(@RequestParam String type, @RequestParam int price) {
        roomRepo.save(new Room(type, price, "Available"));
        return "redirect:/admin";
    }

    // Delete room
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable int id) {
        roomRepo.deleteById(id);
        return "redirect:/admin";
    }

    // Open booking form
    @GetMapping("/book/{roomId}")
    public String bookPage(@PathVariable int roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "booking";
    }

    // Save booking
    @PostMapping("/confirmBooking")
    public String confirmBooking(
            @RequestParam int roomId,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email) {

        Booking booking = new Booking(roomId, name, phone, email);
        bookingRepo.save(booking);

        Room room = roomRepo.findById(roomId);
        room.setStatus("Booked");
        roomRepo.save(room);

        return "redirect:/bookings";
    }

    // View all bookings (REPORT)
    @GetMapping("/bookings")
    public String viewBookings(Model model) {
        model.addAttribute("bookings", bookingRepo.findAll());
        return "bookings";
    }

    // Cancel booking
    @GetMapping("/cancel/{bookingId}")
    public String cancelBooking(@PathVariable int bookingId) {

        Booking booking = bookingRepo.findById(bookingId).get();

        Room room = roomRepo.findById(booking.getRoomId());
        room.setStatus("Available");
        roomRepo.save(room);

        bookingRepo.deleteById(bookingId);

        return "redirect:/bookings";
    }
}
