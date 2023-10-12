package com.example.memberboard.controller;


import com.example.memberboard.dto.MemberDTO;
import com.example.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String save(){
        return "memberPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "redirect:/";
    }


    @GetMapping("/myPage")
    public String findById(HttpSession session, Model model){
        String memberEmail = (String) session.getAttribute("user");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member", memberDTO);
        return "memberPages/update";
    }

    @GetMapping("/admin")
    public String findByAll(Model model, HttpSession session){
        if("admin".equals(session.getAttribute("user"))){
        List<MemberDTO> memberDTOList  = memberService.findByAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/admin";
        }else{
            return "error";
        }
    }


    @GetMapping("/{memberEmail}")
    public ResponseEntity findByEmail(@PathVariable("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        if(memberDTO!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "redirectURI", defaultValue = "/") String redirectURI, Model model){
        System.out.println("redirectURI = " + redirectURI);
        model.addAttribute("redirectURI", redirectURI);
        return "memberPages/login";
    }

    @PostMapping("/login")
    public ResponseEntity login(@ModelAttribute MemberDTO memberDTO, @RequestParam("keep") boolean keep, HttpSession session, HttpServletResponse response, @RequestParam("redirectURI") String redirectURI){
        MemberDTO login = memberService.login(memberDTO);
        if(login!=null){
            session.setAttribute("user", memberDTO.getMemberEmail());
            session.setAttribute("userId", memberDTO.getId());
            if(keep){
                Cookie cookie=new Cookie("memberEmail", memberDTO.getMemberEmail());
                cookie.setMaxAge(60*60*24*7);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return new ResponseEntity<>(redirectURI, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response){
        session.removeAttribute("user");
        session.removeAttribute("userId");
        Cookie cookie=new Cookie("memberEmail", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO)throws IOException{
        System.out.println("memberDTO = " + memberDTO);
        memberService.update(memberDTO);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id, HttpSession session, HttpServletResponse response){
        memberService.delete(id);
        session.removeAttribute("user");
        session.removeAttribute("userId");
        Cookie cookie=new Cookie("memberEmail", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id
    ){
        memberService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
