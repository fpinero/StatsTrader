package com.fpe.statsTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertToSqlDateFormatWithQuotes {
	
	public String converDate(Date fechaAconvertir){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = sdf.format(fechaAconvertir);
		return "'" + fechaFormateada + "'";
		
	}

}
