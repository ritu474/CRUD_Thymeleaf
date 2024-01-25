package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/home")
	public String homePage(Model mv)
	{
		mv.addAttribute("studentlist",studentRepository.findAll());
		return "home";
		
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model m)
	{
		Student student=new Student();
		m.addAttribute(student);
		return "addNewStudent";
	}
	
	@PostMapping("/saveStudent")
	public String saveAStudent(@ModelAttribute("student") Student student)
	{
		studentRepository.save(student);		
		return "redirect:/home";
	}
	
	@GetMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable("id") int id,Model m)
	{
		Optional<Student> temp=studentRepository.findById(id);
		Student student=temp.get();
		m.addAttribute("student",student);
		
		return "updateStudent";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") int id)
	{
		studentRepository.deleteById(id);
		return "redirect:/home";
	}
	
}
