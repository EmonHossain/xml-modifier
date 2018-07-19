package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

@Controller
public class TestController {

	@Autowired
	private RepositoryService repoService;

	@Autowired
	private FlowService flowService;

	@Autowired
	private XMLWriterDom xmlWriterDom;

	@GetMapping("/")
	@ResponseBody
	public String testFlow() throws SAXException, IOException {
		xmlWriterDom.getBpmnFile();
		return "hello";
	}

	@GetMapping("/start")
	@ResponseBody
	public String testFlows() {
		flowService.startProcess();
		return "complete...!!!!";
	}

	@GetMapping("/deploy")
	@ResponseBody
	public String testdeploy() throws IOException {
		Resource resource = new ClassPathResource("/processes/tasks.bpmn20.xml");
		repoService.createDeployment().addInputStream("tasks", new FileInputStream(new File(resource.getURI()))).deploy();
		return "deployed...!!!!";
	}

}
