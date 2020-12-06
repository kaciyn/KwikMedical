package com.example.KwikMedical;

import com.example.KwikMedical.Entities.Patient;
import com.example.KwikMedical.Entities.PatientID;
import com.example.KwikMedical.app.DispatcherApplicationLayer;
import com.example.KwikMedical.app.PatientApplicationLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientLookupController
{
private PatientApplicationLayer patientApplicationLayer;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}


	@GetMapping("/patientlookup")
	public String patientLookupForm(Model model) {
		model.addAttribute("patientId", new PatientID());
		return "patientlookup";
	}

	@PostMapping("/patientlookup")
	public String patientLookupResults(@ModelAttribute PatientID patientId, Model model) {
		Patient patient=patientApplicationLayer.getPatient(patientId.getId());
		model.addAttribute("patient", patient);

		return "result";
	}

}
