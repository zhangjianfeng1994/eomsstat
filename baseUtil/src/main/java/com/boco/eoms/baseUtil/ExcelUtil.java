package com.boco.eoms.baseUtil;



import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;


@SuppressWarnings("deprecation")
public class ExcelUtil {
	/**
	 * author lijin;
	 * discribe this class is base excel util
	 **/
	
	
	/**
	 * export Excel
	 * 
	 **/
	public static void exportExcel(String excelName,HSSFWorkbook wb){
		try{
			OutputStream outFile = new FileOutputStream(excelName);
	        wb.write(outFile);
	        System.out.println("Generate "+excelName+" OK!!!");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error generate "+excelName+"!!!");
			}
	}
	public static OutputStream exportExcelStream(String excelName,HSSFWorkbook wb){
		try{
			OutputStream outFile = new FileOutputStream(excelName);
			//wb.
			System.out.println("Generate "+excelName+" IOStream OK!!!");
			return outFile;
	        
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error generate "+excelName+"!!!");
				return null;
				
			}
		
	}
	
	/**
	 * generate new HSSFWorkbook
	 */
	public static HSSFWorkbook generateNewWorkbook(){
		return new HSSFWorkbook();
	}
	/**
	 *generate new HSSFSheet 
	 */
	public static HSSFSheet generateNewSheet(HSSFWorkbook wb,String sheetName){
		return wb.createSheet(sheetName);
	}
	
	/** 
	 * generate new HSSFRow
	 * cells is value of each cell in row[rowNum]
	 * starIndex is value of begin column,endIndex is (starIndex+cells size)
	 * ToDo:generate a row
	 */
	public static HSSFRow generateNewRow(HSSFSheet sheet,String[] cells,Integer rowNum,Integer starIndex){
		HSSFRow row=getRow(sheet,rowNum);
		for(int i=0;i<cells.length;i++){
			getCell(row,starIndex+i).setCellValue(cells[i]);
		}
		return row;
	}
	/** 
	 * generate new HSSFRow
	 * cells is value of each cell in row[rowNum]
	 * starIndex is value of begin column,endIndex is (starIndex+cells size)
	 * ToDo:generate a row
	 */
	public static HSSFRow generateNewStyleRow(HSSFWorkbook wb,HSSFSheet sheet,String[] cells,Integer rowNum,Integer starIndex){
		HSSFRow row=getRow(sheet,rowNum);
		for(int i=0;i<cells.length;i++){
			getCell(row,starIndex+i).setCellValue(cells[i]);
			getCell(row,starIndex+i).setCellStyle(contentStyleACBorder(wb));
		}
		return row;
	}
	
	
	/**
	 * get HSSFRow 
	 * if row exist then retun else new an HSSFRow
	 */
	public static HSSFRow getRow(HSSFSheet sheet,int rowIndex){
		if(null==sheet.getRow(rowIndex)){
			return sheet.createRow(rowIndex);
		}else{
			return sheet.getRow(rowIndex);
		}
		
	}
	/**
	 * set HSSFRow value
	 * cells is value of each cell in row[rowNum]
	 * starIndex is value of begin column,endIndex is (starIndex+cells size)
	 * ToDo:set the value of a row
	 * */
	public static void setRowValue(HSSFRow row,String[] cells,Integer rowNum,Integer starIndex){
		for(short i=0;i<cells.length;i++){
			row.createCell((short)(starIndex.shortValue()+i)).setCellValue(cells[i]);
		}
	}
	/**
	 * set style HSSFRow value
	 * cells is value of each cell in row[rowNum]
	 * starIndex is value of begin column,endIndex is (starIndex+cells size)
	 * ToDo:set the value of a row
	 * */
	public static void setRowValueAndStyle(HSSFWorkbook wb,HSSFRow row,String[] cells,Integer rowNum,Integer starIndex){
		for(int i=0;i<cells.length;i++){
			HSSFCell cell=getCell(row,starIndex+i);
			cell.setCellValue(cells[i]);
			cell.setCellStyle(contentCellStyle(wb));
		}
	}
	/**
	 *get the HSSFCell
	 *if getCell==null then create Cell 
	 */
	public static HSSFCell getCell(HSSFRow row,int columnIndex){
		if(null==row.getCell(columnIndex)){
			return row.createCell((short)columnIndex);
		}else{
			return row.getCell(columnIndex);
		}
	}
	
	/**
	 *generate new cellRangane and give it value
	 *combine the columns and rows
	 */
	public static void generateRange(HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		sheet.getRow(begRow).createCell(begCol.shortValue()).setCellValue(cellValue);
		sheet.addMergedRegion(new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue()));
		//new CellRangeAddress(begRow,endRow,begCol,endCol)
	}
	
	/**
	 *generate new cellRangene which contain style then give it value
	 *combine the columns and rows
	 */
	public static void generateStyleTitleRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(titleStyle(wb));
		sheet.addMergedRegion(new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue()));
		//new CellRangeAddress(begRow,endRow,begCol,endCol)
	}
	
	public static void generateStyleContentRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAC(wb));
		sheet.addMergedRegion(new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue()));
		//new CellRangeAddress(begRow,endRow,begCol,endCol)
	}
	
	public static void generateStyleContentALRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAL(wb));
		sheet.addMergedRegion(new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue()));
		//new CellRangeAddress(begRow,endRow,begCol,endCol)
	}
	
	public static void generateStyleContentARRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAR(wb));
		sheet.addMergedRegion(new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue()));
		//new CellRangeAddress(begRow,endRow,begCol,endCol)
	}
	
	
	public static void generateStyleContentLastBorderRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAC(wb));
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		//CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo();
		/*
		 * int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow();
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend).setCellStyle(regionCell10(wb));
					getCell(row,colfir).setCellStyle(regionLeftCell4(wb));
				}else{
					//getCell(row,i).setCellStyle(regionCell(wb));
				}
			}			
		}
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionTopCell(wb));
					//getCell(sheet.getRow(rowend),colfir).setCellStyle(regionButtomCell(wb));
				}else{
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell2(wb));
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell8(wb));
					getCell(sheet.getRow(i),colend).setCellStyle(regionCell10(wb));
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionCell3(wb));
							getCell(sheet.getRow(rowfir),colend).setCellStyle(regionCell4(wb));
							getCell(sheet.getRow(rowend),colfir).setCellStyle(regionCell5(wb));
							getCell(sheet.getRow(rowend),colend).setCellStyle(regionCell6(wb));
						}else{
							getCell(sheet.getRow(rowfir),j).setCellStyle(regionCell7(wb));
							getCell(sheet.getRow(rowend),j).setCellStyle(regionCell9(wb));
						}
					}
				}
				
			}
		}
		
		
	}
	
	
	
	
	public static void generateStyleContentBorderRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAC(wb));
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		
		//CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo();
		
		/**
		 *int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow(); 
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend).setCellStyle(regionRightCell(wb));
					getCell(row,colfir).setCellStyle(regionLeftCell(wb));
				}else{
					getCell(row,i).setCellStyle(regionCell(wb));
				}
			}			
		}
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionTopCell(wb));
					getCell(sheet.getRow(rowend),colfir).setCellStyle(regionButtomCell(wb));
				}else{
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell2(wb));
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell8(wb));
					getCell(sheet.getRow(i),colend).setCellStyle(regionCell10(wb));
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionCell3(wb));
							getCell(sheet.getRow(rowfir),colend).setCellStyle(regionCell4(wb));
							getCell(sheet.getRow(rowend),colfir).setCellStyle(regionCell5(wb));
							getCell(sheet.getRow(rowend),colend).setCellStyle(regionCell6(wb));
						}else{
							getCell(sheet.getRow(rowfir),j).setCellStyle(regionCell7(wb));
							getCell(sheet.getRow(rowend),j).setCellStyle(regionCell9(wb));
						}
					}
				}
				
			}
		}
		
		
	}
	
	public static void generateStyleContentBorderALRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleALBorder(wb));
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		
		//CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo();
		
		/**
		 *int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow(); 
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend).setCellStyle(regionRightCell(wb));
					getCell(row,colfir).setCellStyle(regionLeftCell6(wb));
				}else{
					getCell(row,i).setCellStyle(regionCell(wb));
				}
			}			
		}
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionTopCell(wb));
					getCell(sheet.getRow(rowend),colfir).setCellStyle(regionButtomCell(wb));
				}else{
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell2(wb));
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell8(wb));
					getCell(sheet.getRow(i),colend).setCellStyle(regionCell10(wb));
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionCell3(wb));
							getCell(sheet.getRow(rowfir),colend).setCellStyle(regionCell4(wb));
							getCell(sheet.getRow(rowend),colfir).setCellStyle(regionCell5(wb));
							getCell(sheet.getRow(rowend),colend).setCellStyle(regionCell6(wb));
						}else{
							getCell(sheet.getRow(rowfir),j).setCellStyle(regionCell7(wb));
							getCell(sheet.getRow(rowend),j).setCellStyle(regionCell9(wb));
						}
					}
				}
				
			}
		}
		
		
	}
	
	
	public static void generateStyleDateBorderRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAC(wb));
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		
		//CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		
		
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo();
		/**
		 * 	int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow();
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend).setCellStyle(regionCell6(wb));
					getCell(row,colfir).setCellStyle(regionLeftCell2(wb));
				}else{
					getCell(row,i).setCellStyle(regionCell9(wb));
				}
			}			
		}	
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					//getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionTopCell(wb));
					getCell(sheet.getRow(rowend),colfir).setCellStyle(regionButtomCell(wb));
				}else{
					//getCell(sheet.getRow(i),colfir).setCellStyle(regionCell2(wb));
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell8(wb));
					getCell(sheet.getRow(i),colend).setCellStyle(regionCell10(wb));
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionCell3(wb));
							getCell(sheet.getRow(rowfir),colend).setCellStyle(regionCell4(wb));
							getCell(sheet.getRow(rowend),colfir).setCellStyle(regionCell5(wb));
							getCell(sheet.getRow(rowend),colend).setCellStyle(regionCell6(wb));
						}else{
							getCell(sheet.getRow(rowfir),j).setCellStyle(regionCell7(wb));
							getCell(sheet.getRow(rowend),j).setCellStyle(regionCell9(wb));
						}
					}
				}
				
			}
		}
		
		
	}
	
	
	public static void generateStyleRange(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		cell.setCellStyle(contentStyleAC1(wb));
		
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		///CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo(); 
		
		/**
		 * int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow();
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend).setCellStyle(regionRightCell(wb));
					getCell(row,colfir).setCellStyle(regionLeftCell(wb));
				}else{
					getCell(row,i).setCellStyle(regionCell(wb));
				}
			}			
		}
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionTopCell(wb));
					getCell(sheet.getRow(rowend),colfir).setCellStyle(regionButtomCell(wb));
				}else{
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell2(wb));
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell8(wb));
					getCell(sheet.getRow(i),colend).setCellStyle(regionCell10(wb));
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionCell3(wb));
							getCell(sheet.getRow(rowfir),colend).setCellStyle(regionCell4(wb));
							getCell(sheet.getRow(rowend),colfir).setCellStyle(regionCell5(wb));
							getCell(sheet.getRow(rowend),colend).setCellStyle(regionCell6(wb));
						}else{
							getCell(sheet.getRow(rowfir),j).setCellStyle(regionCell7(wb));
							getCell(sheet.getRow(rowend),j).setCellStyle(regionCell9(wb));
						}
					}
				}
				
			}
		}
		
		
	}
	
	public static void generateStyleRange2(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		//cell.setCellStyle(contentStyleAC1(wb));
		
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		///CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo(); 
		
		/**
		 * int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow();
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend);
					getCell(row,colfir);
				}else{
					getCell(row,i);
				}
			}			
		}
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(rowfir),colfir);
					getCell(sheet.getRow(rowend),colfir);
				}else{
					getCell(sheet.getRow(i),colfir);
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir);
					getCell(sheet.getRow(i),colend);
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir);
							getCell(sheet.getRow(rowfir),colend);
							getCell(sheet.getRow(rowend),colfir);
							getCell(sheet.getRow(rowend),colend);
						}else{
							getCell(sheet.getRow(rowfir),j);
							getCell(sheet.getRow(rowend),j);
						}
					}
				}
				
			}
		}
		
		
	}
	
	
	
	public static void generateStyleRange1(HSSFWorkbook wb,HSSFSheet sheet,Integer begRow,Integer endRow,Integer begCol,Integer endCol,String cellValue){
		HSSFCell cell=sheet.getRow(begRow).createCell(begCol.shortValue());
		cell.setCellValue(cellValue);
		
		
		Region range=new Region(begRow,begCol.shortValue(),endRow,endCol.shortValue());
		//CellRangeAddress range=new CellRangeAddress(begRow,endRow,begCol,endCol);
		sheet.addMergedRegion(range);
		
		int colfir=range.getColumnFrom();
		int colend=range.getColumnTo();
		int rowfir=range.getRowFrom();
		int rowend=range.getRowTo();
		/**
		 * int colfir=range.getFirstColumn();
		int colend=range.getLastColumn();
		int rowfir=range.getFirstRow();
		int rowend=range.getLastRow();
		 */
		for(int i=rowfir;i<=rowend;i++){
			for(int j=colfir;j<=colend;j++){
				getCell(getRow(sheet,i),j);
			}
		}
		
		//set border rowfir==rowend
		if(rowfir==rowend){
			HSSFRow row=sheet.getRow(rowfir);
			for(int i=colfir;i<=colend;i++){
				if(i==colfir||i==colend){
					getCell(row,colend).setCellStyle(regionRightCell(wb));
					getCell(row,colfir).setCellStyle(regionLeftCell(wb));
				}else{
					getCell(row,i).setCellStyle(regionCell(wb));
				}
			}			
		}
		//set border colfir==colend
		if(colfir==colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionTopCell(wb));
					getCell(sheet.getRow(rowend),colfir).setCellStyle(regionButtomCell(wb));
				}else{
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell2(wb));
				}
			}
		}
		//set border colfir!=colend and rowfir!=rowend
		if(rowfir!=rowend&&colfir!=colend){
			for(int i=rowfir;i<=rowend;i++){
				if(i==rowfir||i==rowend){
					getCell(sheet.getRow(i),colfir).setCellStyle(regionCell8(wb));
					getCell(sheet.getRow(i),colend).setCellStyle(regionCell10(wb));
					for(int j=colfir;j<=colend;j++){
						if(j==colend){
							getCell(sheet.getRow(rowfir),colfir).setCellStyle(regionCell3(wb));
							getCell(sheet.getRow(rowfir),colend).setCellStyle(regionCell4(wb));
							getCell(sheet.getRow(rowend),colfir).setCellStyle(regionCell5(wb));
							getCell(sheet.getRow(rowend),colend).setCellStyle(regionCell6(wb));
						}else{
							getCell(sheet.getRow(rowfir),j).setCellStyle(regionCell7(wb));
							getCell(sheet.getRow(rowend),j).setCellStyle(regionCell9(wb));
						}
					}
				}
				
			}
		}
		
		cell.setCellStyle(contentStyleAC1(wb));
	}
	/**
	 * All CellStyle
	 **/
	
	/**
	 *title CellStyle 
	 *align center
	 */
	public static HSSFCellStyle titleStyle(HSSFWorkbook wb){
		HSSFCellStyle titleStyle= wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setFont(titleFont(wb));
		return titleStyle;
	}
	
	public static HSSFCellStyle contentStyleAC(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setFont(contentFont1(wb));
		return contentStyle;
	}
	public static HSSFCellStyle contentCellStyle(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	//border
	//this is special for wuhan project
	public static HSSFCellStyle contentStyleAC1(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont1(wb));
		contentStyle.setWrapText(true);
		return contentStyle;
	}
	
	public static HSSFCellStyle contentStyleAL(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setFont(contentFont1(wb));
		return contentStyle;
	}
	public static HSSFCellStyle contentStyleAR(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		contentStyle.setFont(contentFont1(wb));
		return contentStyle;
	}
	
	
	//cell Left Right Bottom Top align left
	public static HSSFCellStyle contentStyleALBorder(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell Left Right Bottom Top 
	public static HSSFCellStyle contentStyleACBorder(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	public static HSSFCellStyle allBorders(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return contentStyle;
	}
	
	//cell Left Bottom Top
	public static HSSFCellStyle regionLeftCell(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	public static HSSFCellStyle regionLeftCell2(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setWrapText(true);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	public static HSSFCellStyle regionLeftCel3(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setWrapText(true);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	public static HSSFCellStyle regionLeftCell4(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setWrapText(true);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	public static HSSFCellStyle regionLeftCell6(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setWrapText(true);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell Right Bottom Top
	public static HSSFCellStyle regionRightCell(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	//cell Bottom Top
	public static HSSFCellStyle regionCell(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell left right
	public static HSSFCellStyle regionCell2(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell top left
	public static HSSFCellStyle regionCell3(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell top right
	public static HSSFCellStyle regionCell4(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell buttom left
	public static HSSFCellStyle regionCell5(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	//cell buttom right
	public static HSSFCellStyle regionCell6(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell top 
	public static HSSFCellStyle regionCell7(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell left
	public static HSSFCellStyle regionCell8(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell bottom
	public static HSSFCellStyle regionCell9(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell right
	public static HSSFCellStyle regionCell10(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	//cell Left Right Top
	public static HSSFCellStyle regionTopCell(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		contentStyle.setWrapText(true);
		
		return contentStyle;
	}
	//cell Left Right Bottom 
	public static HSSFCellStyle regionButtomCell(HSSFWorkbook wb){
		HSSFCellStyle contentStyle=wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setFont(contentFont2(wb));
		return contentStyle;
	}
	
	/**
	 *All FontSyle 
	 */
	public static HSSFFont titleFont(HSSFWorkbook wb){
		HSSFFont fontStyle=wb.createFont();
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontStyle.setFontName("����");
		fontStyle.setFontHeightInPoints((short)18);
		return fontStyle;
	}
	public static HSSFFont contentFont1(HSSFWorkbook wb){
		HSSFFont fontStyle=wb.createFont();
		fontStyle.setFontHeightInPoints((short)11);
		return fontStyle;
	}
	public static HSSFFont contentFont2(HSSFWorkbook wb){
		HSSFFont fontStyle=wb.createFont();
		fontStyle.setFontHeightInPoints((short)10);
		return fontStyle;
	}
}
	

