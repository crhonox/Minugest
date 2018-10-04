
package controller;
import Modelos.Ingrediente;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

public class PDFBuilder extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<Ingrediente> ingredientes = (List<Ingrediente>) model.get("ingredientes");
        
        Image logo = Image.getInstance("C:\\Users\\Sir Lekxas\\Desktop\\Minugest\\web\\resources\\image\\p3b.jpg");
        logo.setAlignment(Element.ALIGN_CENTER);
        doc.add(logo);
        Paragraph par = new Paragraph();
        Font fondescrip = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL,BaseColor.BLACK);
        //doc.add(new Paragraph("Recommended books for Spring framework"));
        par.add(new Phrase("Listado de insumos necesarios.",fondescrip));
        doc.add(par);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);
        
        // define font for table header row
        Font font;
        font=new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(3);
         
        // write table header
        cell.setPhrase(new Phrase("Nombre Ingrediente", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Unidad de Medida", font));
        table.addCell(cell);
         
        // write table row data
        for (Ingrediente aBook : ingredientes) {
            table.addCell(aBook.getNombreIngrediente());
            table.addCell(aBook.getCantidadIngrediente());
            table.addCell(aBook.getUnidadMedida());

            
        }
         
        doc.add(table);
         
    }
 
}