package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest 
{
	
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao()
	{
		Task toDo = new Task();
		toDo.setDueDate(LocalDate.now());
		
		try {
			controller.save(toDo);
			fail("Teste não deveria salvar task");
		} 
		catch (ValidationException e) 
		{
			System.out.println("Mensagem teste 01 - "+e.getMessage());
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData()
	{
		Task toDo = new Task();
		toDo.setTask("Task description");
		
		try {
			controller.save(toDo);
			fail("Teste não deveria salvar task");
		} 
		catch (ValidationException e) 
		{
			System.out.println("Mensagem teste 02 - "+e.getMessage());
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada()
	{
		Task toDo = new Task();
		toDo.setTask("Task description");
		toDo.setDueDate(LocalDate.now().minusDays(1));
		
		try {
			controller.save(toDo);
			fail("Teste não deveria salvar task");
		} 
		catch (ValidationException e) 
		{
			System.out.println("Mensagem teste 03 - "+e.getMessage());
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException
	{
		Task toDo = new Task();
		toDo.setTask("Task description");
		toDo.setDueDate(LocalDate.now());
		controller.save(toDo);
		Mockito.verify(taskRepo).save(toDo);
		
		System.out.println("Teste salvou com sucesso a task");
	}
}
