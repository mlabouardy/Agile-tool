package com.bordeaux.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.bordeaux.entity.Project;
import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.StatusTask;
import com.bordeaux.entity.Task;
import com.bordeaux.entity.TaskDependencies;
import com.bordeaux.entity.pert.Pert;
import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.entity.user.User;
import com.bordeaux.service.BackLogService;
import com.bordeaux.service.ProjectService;
import com.bordeaux.service.SprintService;
import com.bordeaux.service.StatusTaskService;
import com.bordeaux.service.TaskService;
import com.bordeaux.service.TaskDependencyService;
import com.bordeaux.service.user.ProductOwnerService;
import com.bordeaux.service.user.ScrumMasterService;
import com.bordeaux.service.user.ScrumTeamService;
import com.bordeaux.service.user.UserService;


@Service
@Transactional
@Path("ressources")
public class RessourcesRest {

	@Autowired
	private ProjectService ps;
	
	@Autowired
	private SprintService ss;
	
	@Autowired
	private TaskService ts;
	
	@Autowired
	private BackLogService bs;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private ScrumMasterService sms;
	
	@Autowired
	private ScrumTeamService sts;
	
	@Autowired
	private ProductOwnerService pos;
	
	@Autowired
	private TaskDependencyService tds;
	
	@Autowired
	private StatusTaskService statusTS;
	
	
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getProjects")
	public Collection<Project> getProjects(){
		return ps.findAll();
	}
	
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getTask/{id}")
    public Task getTask(@PathParam("id") int id) throws Exception {

		return ts.findTaskById(id);
		
    }
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getTasksFromSprint/{id}")
    public Collection<Task> getTasksFromSprint(@PathParam("id") int id) throws Exception {
		Sprint s = ss.findSprintById(id);
		Collection<Task> ct = s.getTasks();
		return ct;
		
    }
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getDependenciesFrom/{id}")
    public Collection<Task> getDependenciesFrom(@PathParam("id") int id) throws Exception {
		Task t = ts.findTaskById(id);
		TaskDependencies td = tds.findTaskDependenciesByParent(t);
		if(td != null)
			return td.getDependencies();
		return null;
		
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getToDoTasksFromSprint/{id}")
	public Collection<Task> getToDoTasksFromSprint(@PathParam("id") int id) throws Exception {
		Sprint s = ss.findSprintById(id);
		Collection<Task> ct = s.getTasks();
		Collection<Task> res;
		return ct;
		
    }
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getSprintsFromProject/{id}")
	public Collection<Sprint> getSprintsFromProject(@PathParam("id") int id) throws Exception{
		
		return ps.findById(id).getSprintList();
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getSprintsFromScrumMaster/{id}")
	public Collection<Sprint> getSprintsFromScrumMaster(@PathParam("id") int id) throws Exception{
		
		ScrumMaster sm = sms.findUserById(id);
		if(sm != null)
			return ss.findSprintOfUser(sm);
		return null;
		
	}
	
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getCurrentSprintFromScrumMaster/{id}")
	public Sprint getCurrentSprintFromScrumMaster(@PathParam("id") int id) throws Exception{
		
		ScrumMaster sm = sms.findUserById(id);
		Sprint res = null;
		if(sm != null){
			Collection<Sprint> listAssociatedSprint = ss.findSprintOfUser(sm);
			Iterator<Sprint> ite = listAssociatedSprint.iterator();
			Date current = new Date();
			Calendar calres = Calendar.getInstance();
			Calendar caltmp = Calendar.getInstance();
			Calendar calCurrent = Calendar.getInstance();
			calCurrent.setTime(current);
			if(ite.hasNext()){
				res = ite.next();
				calres.setTime(res.getBeginning());
			}
			Sprint tmp;
			while(ite.hasNext()){
				tmp = ite.next();
				caltmp.setTime(tmp.getBeginning());
				if(calres.after(caltmp) && calres.compareTo(calCurrent) <= 0){
					res = tmp;
					calres = caltmp;
				}
			}
			
		}
		
		return res;
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getCurrentPertFromScrumMaster/{id}")
	public String getCurrentPertFromScrumMaster(@PathParam("id") int id) throws Exception{
		
		Sprint currentSprint = getCurrentSprintFromScrumMaster(id);
		
		if (currentSprint != null){
			
			Collection<Task> tasks = currentSprint.getTasks();
			
			if (tasks!=null){
				
				Pert pert = new Pert();
				
				for (Task task : tasks){
					long differenceInMillis = task.getEffectiveEnd().getTime() - task.getBeginning().getTime() ;
					pert.addTask(task.getDescription(), differenceInMillis/86400000);
				}
				
				for (Task task : tasks){
					Collection<Task> dependencies = getDependenciesFrom(task.getId());
					if (dependencies != null){
						for (Task dep : dependencies){
							pert.getTask(task.getDescription()).addDependency(pert.getTask(dep.getDescription()));
						}
					}
				}
				
				pert.closeGraph();
				
				return pert.getGraph();
			}
			
		}
		
		return "null";
		
	}
	
	@POST
	@Path("/setSprintToUser/{sprint_id}/{user_id}")
	public String setSprintToUser(@PathParam("sprint_id") int sprint_id, @PathParam("user_id") int user_id) throws Exception{
		Sprint s = ss.findSprintById(sprint_id);
		ScrumMaster sm = sms.findUserById(user_id);
		System.out.println(sm);
		System.out.println(s);
		if(s != null && sm != null){
			s.setOwner(sm);
			ss.save(s);
			return "Success";
		}
		if(s == null)
			return "Fail : sprint not found ";
		else if(us.findUserById(user_id) != null)
			return "Fail : the user is not a Scrum Master";
		else
			return "Fail : no user corresponding";
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getUsers")
	public Collection<User> getUsers(){
		
		return us.findEveryUsers();
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getScrumMasters")
	public Collection<ScrumMaster> getScrumMasters(){
		return sms.findAll();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getScrumMastersFromProject/{project_id}")
	public Collection<ScrumMaster> getScrumMasters(@PathParam("project_id") int project_id){
		Project p = ps.findById(project_id);
		if(p != null)
			return sms.findScrumMastersByProject(p);
		return null;
	}
	
	
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getDevs")
	public Collection<ScrumTeam> getDevs(){
		return sts.findAll();
	}
	
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getProductOwner")
	public Collection<ProductOwner> getProductOwners(){
		return pos.findAll();
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getStatusTasks")
	public Collection<StatusTask> getStatusTasks(){
		return statusTS.findAll();
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getTasksByStatus/{status_id}")
	public Collection<Task> getTasksByStatus(@PathParam("status_id") int id){
		StatusTask st = statusTS.findStatusTaskByid(id);
		if(st != null)
			return ts.findTasksByStatus(st);
		return null;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getTasksByStatusAndSprint/{status_id}/{sprint_id}")
	public Collection<Task> getTasksByStatusAndSprint(@PathParam("status_id") int statusId, @PathParam("sprint_id") int sprintId){
		
		Sprint s = ss.findSprintById(sprintId);
		if(s!= null){
			Collection<Task> tasksInSprint = s.getTasks(); 
			Iterator<Task> taskIte = tasksInSprint.iterator();
			Collection<Task> res = new ArrayList<Task>();
			Task task_tmp;
			StatusTask st = statusTS.findStatusTaskByid(statusId);
			if(st != null){
				while(taskIte.hasNext()){
					task_tmp = taskIte.next();
					if(task_tmp.getStatus() == st)
						res.add(task_tmp);
				}
				return res;
			}
		}
		return null;
	}
	
	@POST
	@Path("/setStatusToTask/{status_id}/{task_id}")
	public String setStatusToTask(@PathParam("status_id") int statusId, @PathParam("task_id") int taskId){
		System.out.println("/setStatusToTask/"+ statusId + "/" + taskId );
		Task t = ts.findTaskById(taskId);
		StatusTask st = statusTS.findStatusTaskByid(statusId);
		if(t != null && st != null){
			if(st.isTerminal())
				t.setEffectiveEnd(new Date());
			t.setStatus(st);
			ts.save(t);
			return "success";
		}
		else if(t != null && st == null)
			return "no status Found for this id";
		else
			return "no task found for this id";
	}
	
	
}
