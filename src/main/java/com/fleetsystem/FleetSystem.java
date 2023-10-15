package com.fleetsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FleetSystem {
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard/index";
	}
	@GetMapping("/hr")
	public String hr() {
		return "/hr/index";
	}
	@GetMapping("/fleet")
	public String fleet() {
		return "/fleet/index";
	}
	@GetMapping("/helpdesk")
	public String helpdesk() {
		return "/helpdesk/index";
	}
	@GetMapping("/accounts")
	public String accounts() {
		return "/accounts/index";
	}
	@GetMapping("/payroll")
	public String payroll() {
		return "/payroll/index";
	}
	@GetMapping("/parameters")
	public String widget() {
		return "/parameters/index";
	}
	@GetMapping("/security")
	public String security() {
		return "/security/index";
	}
	@GetMapping("/reports")
	public String reports() {
		return "/reports/index";
	}
}
