package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Test;

public class DateUtilsTest 
{
	@Test
	public void deveRetornarTrueParaDatasFuturas()
	{
		LocalDate date = LocalDate.now().plusDays(1);
		System.out.println("Teste 01 - Data futura: "+date);
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDatasPassadas()
	{
		LocalDate date = LocalDate.now().minusDays(1);
		System.out.println("Teste 02 - Data passada: "+date);
		assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDataAtual()
	{
		LocalDate date = LocalDate.now();
		System.out.println("Teste 03 - Data atual: "+date);
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
}
