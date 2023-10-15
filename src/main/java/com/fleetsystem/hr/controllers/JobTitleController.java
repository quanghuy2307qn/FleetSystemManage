package com.fleetsystem.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fleetsystem.hr.models.JobTitle;
import com.fleetsystem.hr.repositories.JobTitleRepository;
import com.fleetsystem.hr.services.JobTitleService;

@Controller
public class JobTitleController {

	@Autowired
	private JobTitleService jobTitleService;

	@GetMapping("/jobTitles")
	public String parameters(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAll();
		model.addAttribute("jobTitles", jobTitles);
		return "/hr/jobTitles/jobTitleList";
	}

	// Get Job Title by id
	@GetMapping("/jobTitle/{id}")
	@ResponseBody
	public JobTitle getById(@PathVariable Integer id) {
		return jobTitleService.findById(id).orElse(null);
	}

	@PostMapping("/jobTitleSave")
	public String save(JobTitle jobTitle) {
		jobTitleService.save(jobTitle);
		return "redirect:/jobTitles";
	}

	@RequestMapping(value = "/jobTitle/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delete(@PathVariable Integer id) {
		jobTitleService.delete(id);
		return "redirect:/jobTitles";
	}
}
