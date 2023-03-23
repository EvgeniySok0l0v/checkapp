package com.sakalou.checkapp.utils;

import com.sakalou.checkapp.dto.ProductDto;
import com.sakalou.checkapp.web.response.CheckResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PdfUtils {

    private static final File FILE = new File("src/main/resources/pdf/Clevertec_Template.pdf");
    private static final int MAIN_TX = 50;
    private static final int MAIN_TY = 650;
    private static final int INFO_TX = 400;
    private static final int INFO_TY = 650;
    private static final int QTY_TX = 50;
    private static final int QTY_TY = 570;

    private static final int NAME_TX = 150;
    private static final int NAME_TY = 570;

    private static final int PRICE_TX = 250;
    private static final int PRICE_TY = 570;

    private static final int DISCOUNT_TX = 350;
    private static final int DISCOUNT_TY = 570;

    private static final int TOTAL_TX = 450;
    private static final int TOTAL_TY = 570;

    private static final int STEP = 20;
    public static void generateCheckPDF(CheckResponse response, int id){

        try {
            PDDocument pdDocument = PDDocument.load(FILE);

            PDPage pdPage = pdDocument.getPage(0);

            PDPageContentStream mainContentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            mainContentStream.beginText();
            mainContentStream.setFont(PDType1Font.TIMES_ROMAN , 16);
            mainContentStream.setLeading(STEP);
            mainContentStream.newLineAtOffset(MAIN_TX, MAIN_TY);
            mainContentStream.showText("CASHIER:");


            mainContentStream.newLine();
            mainContentStream.showText("SHOP:");

            mainContentStream.newLine();
            mainContentStream.showText("DATE&TIME:");

            mainContentStream.endText();
            mainContentStream.close();

            PDPageContentStream infoContentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            infoContentStream.beginText();
            infoContentStream.setFont(PDType1Font.TIMES_ROMAN , 16);
            infoContentStream.setLeading(STEP);
            infoContentStream.newLineAtOffset(INFO_TX, INFO_TY);
            infoContentStream.showText(response.getCashier());

            infoContentStream.newLine();
            infoContentStream.showText(response.getShop());

            infoContentStream.newLine();
            infoContentStream.showText(response.getDateTime());

            infoContentStream.endText();
            infoContentStream.close();

            PDPageContentStream QYTcontentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            QYTcontentStream.beginText();
            QYTcontentStream.setFont(PDType1Font.TIMES_ROMAN , 12);
            QYTcontentStream.setLeading(STEP);
            QYTcontentStream.newLineAtOffset(QTY_TX, QTY_TY);
            QYTcontentStream.showText("QTY");

            PDPageContentStream NAMEcontentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            NAMEcontentStream.beginText();
            NAMEcontentStream.setFont(PDType1Font.TIMES_ROMAN , 12);
            NAMEcontentStream.setLeading(STEP);
            NAMEcontentStream.newLineAtOffset(NAME_TX, NAME_TY);
            NAMEcontentStream.showText("NAME");

            PDPageContentStream PRICEcontentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            PRICEcontentStream.beginText();
            PRICEcontentStream.setFont(PDType1Font.TIMES_ROMAN , 12);
            PRICEcontentStream.setLeading(STEP);
            PRICEcontentStream.newLineAtOffset(PRICE_TX, PRICE_TY);
            PRICEcontentStream.showText("PRICE");

            PDPageContentStream DISCOUNTcontentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            DISCOUNTcontentStream.beginText();
            DISCOUNTcontentStream.setFont(PDType1Font.TIMES_ROMAN , 12);
            DISCOUNTcontentStream.setLeading(STEP);
            DISCOUNTcontentStream.newLineAtOffset(DISCOUNT_TX, DISCOUNT_TY);
            DISCOUNTcontentStream.showText("DISCOUNT");

            PDPageContentStream TOTALcontentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            TOTALcontentStream.beginText();
            TOTALcontentStream.setFont(PDType1Font.TIMES_ROMAN , 12);
            TOTALcontentStream.setLeading(STEP);
            TOTALcontentStream.newLineAtOffset(TOTAL_TX, TOTAL_TY);
            TOTALcontentStream.showText("TOTAL");


            for(ProductDto product : response.getProducts()){

                QYTcontentStream.newLine();
                QYTcontentStream.showText(String.valueOf(product.getQuantity()));

                NAMEcontentStream.newLine();
                NAMEcontentStream.showText(product.getName());

                PRICEcontentStream.newLine();
                PRICEcontentStream.showText(String.valueOf(product.getPrice()));

                DISCOUNTcontentStream.newLine();
                DISCOUNTcontentStream.showText(String.valueOf(product.getDiscount()));

                TOTALcontentStream.newLine();
                TOTALcontentStream.showText(String.valueOf(product.getTotalPrice()));
            }

            QYTcontentStream.endText();
            QYTcontentStream.close();

            NAMEcontentStream.endText();
            NAMEcontentStream.close();

            PRICEcontentStream.endText();
            PRICEcontentStream.close();

            DISCOUNTcontentStream.endText();
            DISCOUNTcontentStream.close();

            TOTALcontentStream.endText();
            TOTALcontentStream.close();

            //bottom
            int bottom_start_y =  530 - (response.getProducts().size()*STEP);
            System.out.println(bottom_start_y);
            PDPageContentStream bottomMainContentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            bottomMainContentStream.beginText();
            bottomMainContentStream.setFont(PDType1Font.TIMES_ROMAN , 16);
            bottomMainContentStream.setLeading(STEP);
            bottomMainContentStream.newLineAtOffset(MAIN_TX, bottom_start_y);
            bottomMainContentStream.showText("TOTAL DISCOUNT:");


            bottomMainContentStream.newLine();
            bottomMainContentStream.showText("CARD LEVEL:");

            bottomMainContentStream.newLine();
            bottomMainContentStream.showText("TOTAL PRICE:");

            bottomMainContentStream.endText();
            bottomMainContentStream.close();

            PDPageContentStream bottomInfoContentStream = new PDPageContentStream(pdDocument,
                    pdPage, PDPageContentStream.AppendMode.APPEND, false);

            bottomInfoContentStream.beginText();
            bottomInfoContentStream.setFont(PDType1Font.TIMES_ROMAN , 16);
            bottomInfoContentStream.setLeading(STEP);
            bottomInfoContentStream.newLineAtOffset(INFO_TX, bottom_start_y);
            bottomInfoContentStream.showText(String.valueOf(response.getTotalDiscount()));

            bottomInfoContentStream.newLine();
            bottomInfoContentStream.showText(response.getCardLevel());

            bottomInfoContentStream.newLine();
            bottomInfoContentStream.showText(String.valueOf(response.getTotalPrice()));

            bottomInfoContentStream.endText();
            bottomInfoContentStream.close();


            pdDocument.save("src/main/resources/pdf/report" + id + ".pdf");
            pdDocument.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
