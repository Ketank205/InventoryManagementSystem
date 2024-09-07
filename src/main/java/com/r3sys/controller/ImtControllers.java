package com.r3sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.r3sys.Dao.IssueProcessedDao;
import com.r3sys.Dao.IssueRawDao;
import com.r3sys.Dao.ManagerDao;
import com.r3sys.Dao.ProcessedItemDao;
import com.r3sys.Dao.RawMaterialDao;
import com.r3sys.Models.IssueProcessed;
import com.r3sys.Models.IssueRaw;
import com.r3sys.Models.Manager;
import com.r3sys.Models.ProcessedItem;
import com.r3sys.Models.RawMaterial;

@Controller
public class ImtControllers {
	
	@Autowired
	RawMaterial rm;
	@Autowired
	ProcessedItem pi;
	@Autowired
	IssueProcessed ip;
	@Autowired
	IssueRaw ir;
	@Autowired
	Manager m;
	
	@Autowired
	RawMaterialDao rmDao;
	@Autowired
	ProcessedItemDao piDao;
	@Autowired
	IssueProcessedDao ipDao;
	@Autowired
	IssueRawDao irDao;
	@Autowired
	ManagerDao mDao;
	
	@RequestMapping("/Home")
	public String toHomePage(){
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "login";
	}
	
	
	@RequestMapping("/")
	public String loginPage(){
		return "login";
	}
	@RequestMapping(path="/fromLogin", method = RequestMethod.POST)
	public String fromLoginPage(HttpServletRequest request){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(mDao.adminAuthentication(email, password)==1){
			return "redirect:/Home";
		}
		else{
			return "redirect:/";
		}
	}
	
	
	@RequestMapping("/RawMaterialServices")
	public String toRawMaterialServicesPage(){
		return "RawMaterialServices";
	}
	
	@RequestMapping("/ProcessedItemServices")
	public String toProcessedMaterialServicesPage(){
		return "ProcessedItemServices";
	}
	
	
	@RequestMapping("/toAddRawMaterial")
	public String toAddRawMaterialPage(){
		return "AddRawMaterial";
	}
	@RequestMapping(path="/addRawMaterial", method = RequestMethod.POST)
	public String addRawMaterialPage(HttpServletRequest request){
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String unit = request.getParameter("unit");
		float costPerUnit = Float.parseFloat(request.getParameter("costPerUnit"));
		
		rm.setName(name);
		rm.setQuantity(quantity);
		rm.setUnit(unit);
		rm.setCostPerUnit(costPerUnit);
		
		int i = rmDao.addRawMaterial(rm);
		return "redirect:/RawMaterialServices";
	}
	
	
	@RequestMapping("/ViewRawMaterial")
	public String toViewRawMaterialPage(Model model,HttpServletRequest request){
		List<RawMaterial> rms = rmDao.viewRawMaterial();
		model.addAttribute("rawMaterial", rms);
		return "ViewRawMaterial";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		rmDao.deleteRawMaterial(id);
		return "redirect:/ViewRawMaterial";
	}
	
	
	@RequestMapping("/toUpdateRawMaterial")
	public String toUpdateRawMaterialPage(){
		return "UpdateRawMaterial";
	}
	@RequestMapping(path="/updateRawMaterialQuantity", method = RequestMethod.POST)
	public String updateRawMaterialQuantity(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
		rmDao.updateRawMaterial(id,newQuantity);
		return "redirect:/ViewRawMaterial";
	}
	
	
	@RequestMapping("/toIssueRawMaterial")
	public String toIssueRawMaterialPage(){
		return "IssueRawMaterial";
	}
	@RequestMapping(path="/issueRawMaterial", method = RequestMethod.POST)
	public String issueRawMaterial(HttpServletRequest request){
		int rawId = Integer.parseInt(request.getParameter("rawId"));
		String rawName = request.getParameter("rawName");
		String issuerName = request.getParameter("issuerName");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String issueDate = request.getParameter("issueDate");
		String issueTime = request.getParameter("issueTime");
		
		ir.setRawId(rawId);
		ir.setRawName(rawName);
		ir.setIssuerName(issuerName);
		ir.setQuantity(quantity);
		ir.setIssueDate(issueDate);
		ir.setIssueTime(issueTime);
		
		int i = irDao.issueRawMaterial(ir,rawId,quantity);
		if(i==0){
			return "redirect:/toIssueRawMaterial";
		}
		else{
			return "redirect:/RawMaterialServices";
		}
	}
	
	
	@RequestMapping("/ViewIssueRawMaterial")
	public String toViewIssueRawMaterialPage(Model model, HttpServletRequest request){
		List<IssueRaw> irs = irDao.viewIssueRawMaterial();
		model.addAttribute("issueRaw", irs);
		return "ViewIssueRawMaterial";
	}
	@RequestMapping("/deleteIssueRaw")
	public String deleteIssueRaw(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		irDao.deleteIssueRaw(id);
		return "redirect:/ViewRawMaterial";
	}
	
	
	@RequestMapping("/UnavailableRawMaterial")
	public String UnavailableRawMaterialPage(Model model, HttpServletRequest request){
		List<RawMaterial> rms = rmDao.viewRawMaterial();
		model.addAttribute("unavailableList", rms);
		return "redirect:/UnavailableRawMaterial";
	}
	
	//Proceesed Item Controller Section
	
	
	@RequestMapping("/toAddProcessedItem")
	public String toAddProcessedItem(){
		return "AddProcessedItem";
	} 
	@RequestMapping(path="/addProcessedItem", method = RequestMethod.POST)
	public String addProcessedItemPage(HttpServletRequest request){
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String unit = request.getParameter("unit");
		float costPerUnit = Float.parseFloat(request.getParameter("costPerUnit"));
		
		pi.setName(name);
		pi.setQuantity(quantity);
		pi.setUnit(unit);
		pi.setCostPerUnit(costPerUnit);
		
		int i = piDao.addProcessedItem(pi);
		return "redirect:/ProcessedItemServices";
	}
	
	@RequestMapping("/ViewProcessedItem")
	public String toViewProcessedItemPage(Model model,HttpServletRequest request){
		List<ProcessedItem> pis = piDao.viewProcessedItem();
		model.addAttribute("processedItem", pis);
		return "ViewProcessedItem";
	}
	@RequestMapping("/deleteProcessedItem")
	public String deleteProcessedItem(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		piDao.deleteProcessedItem(id);
		return "redirect:/ViewRawMaterial";
	}
	
	
	@RequestMapping("/toUpdateProcessedItem")
	public String toUpdateProcessedItemPage(){
		return "UpdateProcessedItem";
	}
	@RequestMapping(path="/updateProcessedItemQuantity", method = RequestMethod.POST)
	public String updateProcessedItemQuantity(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
		piDao.updateProcessedItem(id,newQuantity);
		return "redirect:/ViewProcessedItem";
	}
	
	
	@RequestMapping("/toIssueProcessedItem")
	public String toIssueProcessedItemPage(){
		return "IssueProcessedItem";
	}
	@RequestMapping(path="/issueProcessedItem", method = RequestMethod.POST)
	public String issueProcessedItem(HttpServletRequest request){
		int processedId = Integer.parseInt(request.getParameter("processedId"));
		String processedName = request.getParameter("processedName");
		String issuerName = request.getParameter("issuerName");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String issueDate = request.getParameter("issueDate");
		String issueTime = request.getParameter("issueTime");
		
		ip.setProcessedId(processedId);
		ip.setProcessedName(processedName);
		ip.setIssuerName(issuerName);
		ip.setQuantity(quantity);
		ip.setIssueDate(issueDate);
		ip.setIssueTime(issueTime);
		
		int i = ipDao.issueProcessedItem(ip,processedId,quantity);
		if(i==0){
			return "redirect:/toIssueProcessedItem";
		}
		else{
			return "redirect:/ProcessedItemServices";
		}
	}
	
	
	@RequestMapping("/ViewIssueProcessedItem")
	public String toViewIssueProcessedItemPage(Model model, HttpServletRequest request){
		List<IssueProcessed> ips = ipDao.viewIssueProcessedItem();
		model.addAttribute("issueProcessed", ips);
		return "ViewIssueProcessedItem";
	}
	@RequestMapping("/deleteIssueProcessedItem")
	public String deleteIssueProcessedItem(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		ipDao.deleteIssueProcessedItem(id);
		return "redirect:/ViewProcessedItem";
	}
	
	
	@RequestMapping("/UnavailableProcessedItem")
	public String UnavailableProcessedItemPage(Model model,HttpServletRequest request){
		List<ProcessedItem> pis = piDao.viewProcessedItem();
		model.addAttribute("unavailableProcessedItem", pis);
		return "UnavailableProcessedItem";
	}
	
	
	@RequestMapping("/ChangePassword")
	public String changePassword(){
		return "AdminChangePassword";
	}
	@RequestMapping("/adminChangePassword")
	public String adminChangePassword(HttpServletRequest request){
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newPassword");
		int i = mDao.changePassword(email,newPassword);
		if(i==1){
			return "redirect:/";
		}
		else{
			return "redirect:/ChangePassword";
		}
	}
}
