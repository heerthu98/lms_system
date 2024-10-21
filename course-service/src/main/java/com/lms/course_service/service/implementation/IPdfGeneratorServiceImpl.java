package com.lms.course_service.service.implementation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lms.course_service.dto.CourseDto;
import com.lms.course_service.service.IPdfGeneratorService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Queue;

@Service
public class IPdfGeneratorServiceImpl implements IPdfGeneratorService {
    @Override
    public byte[] generateCoursePdf(Queue<CourseDto> courses) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add a title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph title = new Paragraph("List of Courses in LMS-Creative System", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("This document contains a list of all available courses in the system along with their description, duration, and other details.",normalFont ));
            document.add(new Paragraph(" "));

            // Create a table with 7 columns
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);


            // Add table headers
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Course Name", headerFont));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Course Description", headerFont));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Course Duration", headerFont));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Created At", headerFont));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Created By", headerFont));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Updated At", headerFont));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Updated By", headerFont));


            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);


            // Add rows to the table from the FIFO queue
            for (CourseDto course : courses) {
                table.addCell(String.valueOf(course.getCourseName()));
                table.addCell(course.getCourseDescription());
                table.addCell(course.getCourseDuration());
                table.addCell(String.valueOf(course.getCreatedAt()));
                table.addCell(String.valueOf(course.getCreatedBy()));
                table.addCell(String.valueOf(course.getUpdatedAt()));
                table.addCell(String.valueOf(course.getUpdatedBy()));
            }

            // Add the table to the document
            document.add(table);

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return outputStream.toByteArray();
    }
}