package com.example.PServer;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

@Controller
public class ImageController {
//    @GetMapping("image")
//    public String image(Model model) throws IOException {
//        String base64;
//        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Vilembraw\\IdeaProjects\\PClient\\tmp\\data.txt"));
//        base64 = reader.readLine();
//        model.addAttribute("image", base64);
//        return "eegimage";
//    }

    @GetMapping("{user}/{electrode}")
    public String test(Model model, @PathVariable String user, @PathVariable int electrode){
        model.addAttribute("username", user);
        model.addAttribute("electrode", electrode);
        String url = "jdbc:sqlite:C:\\Users\\Vilembraw\\IdeaProjects\\PClient\\usereeg.db";
        String sql = "SELECT image FROM user_eeg WHERE username =\""+user+"\" AND electrode_number ="+electrode;
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(rs.getString("image"));
            model.addAttribute("image",rs.getString("image"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return "eggimage";
    }
}