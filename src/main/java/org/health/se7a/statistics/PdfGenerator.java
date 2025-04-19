package org.health.se7a.statistics;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {
    private static final BaseColor HEADER_COLOR = new BaseColor(13, 110, 253);
    private static final BaseColor ACCENT_COLOR = new BaseColor(111, 66, 193);
    private static final Font SECTION_FONT = FontFactory.getFont(
            FontFactory.HELVETICA_BOLD, 14, ACCENT_COLOR);

    public static byte[] generateAdvancedStatisticsPdf(StatisticsResponseDTO dto, String logoPath) {
        try {
            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, out);

            // Add header with logo
            HeaderFooter event = new HeaderFooter(logoPath);
            writer.setPageEvent(event);

            document.open();

            addTitle(document);
            addStatisticsSection(document, dto);
            addChartsSection(document, dto);
            addFooter(document);

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("error.pdf.generation");
        }
    }

    private static void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("HEALTH DATA STATISTICS REPORT",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, ACCENT_COLOR));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(30f);
        document.add(title);
    }

    private static void addStatisticsSection(Document document, StatisticsResponseDTO dto)
            throws DocumentException {

        Paragraph sectionTitle = new Paragraph("General Statistics", SECTION_FONT);
        sectionTitle.setSpacingAfter(15f);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(20f);

        addTableHeader(table, "Metric");
        addTableHeader(table, "Value");

        addTableRow(table, "Date Range", dto.getRange());
        addTableRow(table, "Total Lab Tests", String.valueOf(dto.getTotalLabTests()));
        addTableRow(table, "Total Medications", String.valueOf(dto.getTotalMedications()));
        addTableRow(table, "Vital Signs Recorded", String.valueOf(dto.getTotalVitalSigns()));

        document.add(table);
    }

    private static void addChartsSection(Document document, StatisticsResponseDTO dto)
            throws DocumentException, IOException {

        Paragraph sectionTitle = new Paragraph("Data Distribution", SECTION_FONT);
        sectionTitle.setSpacingAfter(15f);
        document.add(sectionTitle);

        // Generate chart
        JFreeChart chart = createBarChart(dto);
        BufferedImage chartImage = chart.createBufferedImage(600, 400);
        Image pdfChartImage = Image.getInstance(chartImage, null);
        pdfChartImage.setAlignment(Image.ALIGN_CENTER);
        pdfChartImage.scaleToFit(500, 300);
        document.add(pdfChartImage);
    }

    private static JFreeChart createBarChart(StatisticsResponseDTO dto) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(dto.getTotalLabTests(), "Tests", "Lab Tests");
        dataset.addValue(dto.getTotalMedications(), "Medications", "Medications");
        dataset.addValue(dto.getTotalVitalSigns(), "Readings", "Vital Signs");

        JFreeChart chart = ChartFactory.createBarChart(
                "Health Data Distribution",
                "Category",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(java.awt.Color.WHITE);
        return chart;
    }

    private static void addTableHeader(PdfPTable table, String text) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(HEADER_COLOR);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(text,
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
        table.addCell(header);
    }

    private static void addTableRow(PdfPTable table, String label, String value) {
        table.addCell(createCell(label));
        table.addCell(createCell(value));
    }

    private static PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    private static void addFooter(Document document) throws DocumentException {
        Paragraph footer = new Paragraph("Generated by HealthSE7A - Confidential Report",
                FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY));
        footer.setSpacingBefore(20f);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }

    static class HeaderFooter extends PdfPageEventHelper {
        private Image logo;

        public HeaderFooter(String logoPath) throws IOException, BadElementException {
            this.logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50);
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte canvas = writer.getDirectContent();
                logo.setAbsolutePosition(40, document.top() + 20);
                canvas.addImage(logo);

                // Add page number
                ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                        new Phrase(String.format("Page %d", writer.getPageNumber()),
                                FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY)),
                        297.5f, 30, 0);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }
    }
}