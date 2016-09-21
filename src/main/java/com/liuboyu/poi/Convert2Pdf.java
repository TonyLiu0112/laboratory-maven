package com.liuboyu.poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * 将word文档转换成pdf文件
 * <p>
 * Created by Tony on 9/14/16.
 */
public class Convert2Pdf {
    public static void main(String[] args) throws Exception {
        String path = "/tmp/";
        String inputPath = "/Users/liuboyu/Downloads/Storm.docx";
//        convert2Doc(inputPath, path);
        addHeader(inputPath);
    }

    public static void addHeader(String inputPath) throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(inputPath));
        CTP ctp = CTP.Factory.newInstance();
        CTR ctr = ctp.addNewR();
        CTText textt = ctr.addNewT();
        textt.setStringValue("奇硕科技");
        XWPFParagraph codePara = new XWPFParagraph(ctp, document);
        codePara.setAlignment(ParagraphAlignment.CENTER);
        codePara.setVerticalAlignment(TextAlignment.CENTER);
        XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
        newparagraphs[0] = codePara;
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, newparagraphs);
        OutputStream os = new FileOutputStream("/tmp/rile.doc");
        // 页脚的页码
        simpleNumberFooter(document);
        document.write(os);
        os.close();
    }

    //页脚:显示页码信息
    public static void simpleNumberFooter(XWPFDocument document) throws Exception {
        CTP ctp = CTP.Factory.newInstance();
        XWPFParagraph codePara = new XWPFParagraph(ctp, document);
        XWPFRun r1 = codePara.createRun();
        r1.setText("第");
        r1.setFontSize(11);
        CTRPr rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
        CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
        fonts.setAscii("宋体");
        fonts.setEastAsia("宋体");
        fonts.setHAnsi("宋体");

        r1 = codePara.createRun();
        CTFldChar fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("begin"));

        r1 = codePara.createRun();
        CTText ctText = r1.getCTR().addNewInstrText();
        ctText.setStringValue("PAGE  \\* MERGEFORMAT");
        ctText.setSpace(SpaceAttribute.Space.Enum.forString("preserve"));
        r1.setFontSize(11);
        rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
        fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
        fonts.setAscii("宋体");
        fonts.setEastAsia("宋体");
        fonts.setHAnsi("宋体");

        fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("end"));

        r1 = codePara.createRun();
        r1.setText("页 总共");
        r1.setFontSize(11);
        rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
        fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
        fonts.setAscii("宋体");
        fonts.setEastAsia("宋体");
        fonts.setHAnsi("宋体");

        r1 = codePara.createRun();
        fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("begin"));

        r1 = codePara.createRun();
        ctText = r1.getCTR().addNewInstrText();
        ctText.setStringValue("NUMPAGES  \\* MERGEFORMAT ");
        ctText.setSpace(SpaceAttribute.Space.Enum.forString("preserve"));
        r1.setFontSize(11);
        rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
        fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
        fonts.setAscii("宋体");
        fonts.setEastAsia("宋体");
        fonts.setHAnsi("宋体");

        fldChar = r1.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("end"));

        r1 = codePara.createRun();
        r1.setText("页");
        r1.setFontSize(11);
        rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
        fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
        fonts.setAscii("宋体");
        fonts.setEastAsia("宋体");
        fonts.setHAnsi("宋体");

        codePara.setAlignment(ParagraphAlignment.CENTER);
        codePara.setVerticalAlignment(TextAlignment.CENTER);
        codePara.setBorderTop(Borders.THICK);
        XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
        newparagraphs[0] = codePara;
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, newparagraphs);
    }

    public static void convert2Doc(String inputPath, String path) throws Exception {
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(inputPath));

        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

        wordToHtmlConverter.setPicturesManager((content, pictureType, suggestedName, widthInches, heightInches) -> suggestedName);
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(path
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray());
        FileUtils.write(new File(path, "1.html"), content, "utf-8");
    }

    public static void convert2Docx(String inputPath, String path) throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(inputPath));

        XHTMLOptions options = XHTMLOptions.create().indent(4);
        OutputStream out = System.out;
        XHTMLConverter.getInstance().convert(document, out, options);
    }

}
