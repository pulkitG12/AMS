package com.pulkit.AMS;

import org.springframework.web.servlet.ModelAndView;

import com.pulkit.dao.empDao;
import com.pulkit.dao.goalDao;
import com.pulkit.domain.Emp;
import com.pulkit.domain.Goals;
import com.pulkit.domain.HR;
import com.pulkit.domain.Manager;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class MainController {
	
	
	private LinkedList<Goals> l=new LinkedList<Goals>();
	private LinkedList<Goals> selected=new LinkedList<Goals>();
	private LinkedList<Goals> approved=new LinkedList<Goals>();

	
	@RequestMapping(value="/employee")
	public ModelAndView emplogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("employeeLogin");
		return mv;
	}
	@RequestMapping(value="/manager")
	public ModelAndView manglogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("managerLogin");
		return mv;
	}
	
	@RequestMapping(value="/hr")
	public ModelAndView hrlogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("hrLogin");
		return mv;
	}
	@RequestMapping(value="/view")
	public ModelAndView viewEmp()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("viewEmp");
		return mv;
	}
	@RequestMapping(value="/appraisal")
	public ModelAndView appraisal()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("appraisal");
		return mv;
	}
	
	
	/*
	 * FINAL
	 */
	@RequestMapping(value="/login1")
	public ModelAndView processEmp(@Valid Emp emp,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("emp") Emp emp,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		empDao ed=new empDao();
		ed.createEmp(emp);
		mv.setViewName("empDash");
		mv.addObject("e",emp);
		return mv;
	}
	
		
	@RequestMapping(value="/login2")
	public ModelAndView processMang(Manager manager)
	//public ModelAndView processEmp(@ModelAttribute("manager") Manager manager,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mangDash");
		mv.addObject("m",manager);
		return mv;
	}
	
	@RequestMapping(value="/login3")
	public ModelAndView processHr(HR hr) throws Exception
	//public ModelAndView processEmp(@ModelAttribute("hr") HR hr,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		
		if(hr.getHrid().length()<8)
			throw new Exception();
		if(hr.getHrid().length()>9)
			throw new UserNotFound();
		mv.setViewName("hrDash");
		mv.addObject("h",hr);
		return mv;
	}
	
	
	@RequestMapping(value="/redirect")
	public ModelAndView redirectHr()
	//public ModelAndView processEmp(@ModelAttribute("hr") HR hr,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		
		mv.setViewName("hrDash");
		return mv;
	}
	
	
	
	
	
	/*
	 * FINAL
	 */
	@RequestMapping(value="/goals")
	public ModelAndView openGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		goalDao d=new goalDao();
		List<Goals> ll=d.getAllGoals();
		mv.addObject("lists",ll);
		mv.setViewName("setGoals");
		return mv;
	}
	/*
	 * FINAL
	 */
	@RequestMapping(value="/setgoals")
	public ModelAndView setGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		goalDao dd=new goalDao();
		List<Goals> ll=dd.getAllGoals();
		mv.addObject("lists",ll);
		if(!result.hasErrors())
			{
			goalDao d=new goalDao();
			d.createGoal(goals);
			}
		mv.setViewName("setGoals");
		return mv;
	}
	
	/*
	 * FINAL
	 */
	@RequestMapping(value="/setgoals/{id}")
	public ModelAndView updateGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		goalDao dd=new goalDao();
		List<Goals> ll=dd.getAllGoals();
		mv.addObject("lists",ll);
		if(!result.hasErrors())
			{
			goalDao d=new goalDao();
			d.updateGoal(goals);
			}
		mv.setViewName("setGoals");		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/selectgoals")
	public ModelAndView selectGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("lists",l);
		mv.setViewName("selectGoals");
		return mv;
	}
	/*
	 * FINAL
	 */
	@RequestMapping(value="/selectgoals/{id}")
	public ModelAndView selectedGoal(@PathVariable int id)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		goalDao dd=new goalDao();
		List<Goals> ll=dd.getAllGoals();
		mv.addObject("lists",ll);
		empDao ed=new empDao();
		mv.addObject("e",ed.readEmp(id));
		List<Goals> selected=ed.selectedGoals(id);
		mv.addObject("select",selected);
		mv.setViewName("selectGoals");
		return mv;
	}
	
	
	
	@RequestMapping(value="search/{id}")
	public ModelAndView searchGoal(@PathVariable int id,@Valid int search,BindingResult result)
	{
		ModelAndView mv=new ModelAndView();
		goalDao dd=new goalDao();
		List<Goals> ll=new LinkedList<Goals>();
		ll.add(dd.readGoal(search));
		mv.addObject("lists",ll);
		empDao ed=new empDao();
		mv.addObject("e",ed.readEmp(id));
		List<Goals> selected=ed.selectedGoals(id);
		mv.addObject("select",selected);
		mv.setViewName("selectGoals");
		return mv;
	}
	
	/*
	 * FINAL
	 */
	@RequestMapping(value="/viewEmp")
	public ModelAndView viewEmployees()
	{
		ModelAndView mv=new ModelAndView();
		
		empDao ed=new empDao();
		List<Emp> l=new LinkedList<Emp>();
		l=ed.getAllEmp();
		mv.addObject("l",l);
		mv.setViewName("viewEmp");
		return mv;
	}
	
	@RequestMapping(value="/viewEmp/{empid}")
	public ModelAndView viewEmployee(@PathVariable int empid)
	{
		ModelAndView mv=new ModelAndView();
		
		empDao ed=new empDao();
		List<Goals> l=new LinkedList<Goals>();
		l=ed.selectedGoals(empid);
		mv.addObject("l",l);
		mv.addObject("e",empid);
		mv.setViewName("viewEmpGoals");
		return mv;
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/approvegoals")
	public ModelAndView approveGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("lists",selected);
		
		
		mv.setViewName("approveGoals");

		
		return mv;
	}
	@RequestMapping(value="/approvegoals/{id}")
	public ModelAndView appGoal(@PathVariable int id)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		//mv.addObject("g",goals);
		
		//Goals g=new Goals();
		for(int i=0;i<l.size();i++)
		{
			Goals g=new Goals();
			g=selected.get(i);
			if(g.getId()!=0 && g.getId()==(id))
			{
				approved.add(g);
				selected.remove(i);
				break;
			}
		}
		mv.addObject("lists",selected);

		mv.setViewName("approveGoals");

		
		return mv;
	}
	
	@RequestMapping(value="/evaluate")
	public ModelAndView evaluateGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("lists",approved);
		
		
		mv.setViewName("evaluateGoals");

		
		return mv;
	}
	
	@RequestMapping(value="/evaluate/{id}")
	public ModelAndView selfevaluateGoal(@Valid Goals goals,BindingResult result)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
	
		mv.setViewName("selfevaluateGoals");

		
		return mv;
	}
	
	
	
	
	
	
	/*
	@RequestMapping(value="/add/{id}")
	public ModelAndView addGoal(@PathVariable int id)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		//mv.addObject("g",goals);
		//Goals g=new Goals();
		for(int i=0;i<l.size();i++)
		{
			Goals g=new Goals();
			g=l.get(i);
			if(g.getId()!=0 && g.getId()==(id))
			{
				selected.add(g);
				break;
			}
		}
		//l.remove(g);
		mv.addObject("lists",selected);
		mv.setViewName("selectedGoals");		
		return mv;
	}
	*/
	/*
	 * FINAL
	 */
	@RequestMapping(value="add/{empid}/{id}")
	public ModelAndView addGoals(@PathVariable int id,@PathVariable int empid)
	{
		ModelAndView mv=new ModelAndView();
		
		goalDao gd=new goalDao();
		empDao ed=new empDao();
		Emp e=ed.readEmp(empid);
		System.out.println(e);
		ed.addGoal(e,gd.readGoal(id));
		List<Goals> ll=gd.getAllGoals();
		mv.addObject("lists",ll);
		mv.addObject("e",e);
		List<Goals> selected=ed.selectedGoals(empid);
		mv.addObject("select",selected);
		mv.setViewName("selectGoals");
		return mv;
	}
	/*
	 * FINAL
	 */
	@RequestMapping(value="delete/{empid}/{id}")
	public ModelAndView deleteGoals(@PathVariable int id,@PathVariable int empid)
	{
		ModelAndView mv=new ModelAndView();
		
		goalDao gd=new goalDao();
		empDao ed=new empDao();
		ed.delGoal(empid,id);
		List<Goals> ll=gd.getAllGoals();
		mv.addObject("lists",ll);
		mv.addObject("e",ed.readEmp(empid));
		List<Goals> selected=ed.selectedGoals(empid);
		mv.addObject("select",selected);
		mv.setViewName("selectGoals");
		return mv;
	}
	/*
	 * FINAL
	 */
	@RequestMapping(value="/delete/{id}")
	public ModelAndView delGoal(@PathVariable int id)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();	
				goalDao d=new goalDao();
				d.deleteGoal(id);
		goalDao dd=new goalDao();
		List<Goals> ll=dd.getAllGoals();
		mv.addObject("lists",ll);
		mv.setViewName("setGoals");
		return mv;
	}
	/*
	 * FINAL
	 */
	@RequestMapping(value="/edit/{id}")
	public ModelAndView editGoal(@PathVariable int id)
	//public ModelAndView processEmp(@ModelAttribute("goals") Goals goals,BindingResult result,ModelAndView mv)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("lists",l);
		goalDao dd=new goalDao();
		Goals ll=dd.readGoal(id);
		mv.addObject("goals",ll);
		mv.setViewName("updateGoal");
		return mv;
	}
	
	
	
	
	
	
	
	@ExceptionHandler(UserNotFound.class)
	public ModelAndView handleException(UserNotFound e)
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("msg",e.getMessage());
		return mv;
	}
	@ModelAttribute("emp")
	public Emp loginEmp()
	{
		Emp emp=new Emp();
		return emp;
	}
	@ModelAttribute("manager")
	public Manager loginMang()
	{
		Manager mang=new Manager();
		return mang;
	}
	
	@ModelAttribute("hr")
	public HR loginHr()
	{
		HR hr=new HR();
		return hr;
	}
	@ModelAttribute("goals")
	public Goals setGoals()
	{
		Goals goals=new Goals();
		return goals;
	}
}

