package com.example.xdd1;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser {

    public static List<ListElements> parseContacts(Context context) {
        List<ListElements> elements = new ArrayList<>();
        File file = new File(context.getFilesDir(), "contacts.xml");

        if (!file.exists()) {
            copyRawFileToInternalStorage(context, file);
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList contactNodes = doc.getElementsByTagName("contact");
            for (int i = 0; i < contactNodes.getLength(); i++) {
                Element contactElement = (Element) contactNodes.item(i);
                String name = contactElement.getElementsByTagName("nombre").item(0).getTextContent();
                String phone = contactElement.getElementsByTagName("tel").item(0).getTextContent();
                String id = contactElement.getElementsByTagName("id").item(0).getTextContent();

                ListElements contact = new ListElements(name, phone, id);
                elements.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return elements;
    }

    private static void copyRawFileToInternalStorage(Context context, File file) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.contacts);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveContacts(Context context, List<ListElements> elements) {
        try {
            File file = new File(context.getFilesDir(), "contacts.xml");
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write("<contacts>\n");
            for (ListElements element : elements) {
                fileWriter.write("<contact>\n");
                fileWriter.write("    <nombre>" + element.getNombre() + "</nombre>\n");
                fileWriter.write("    <tel>" + element.getTel() + "</tel>\n");
                fileWriter.write("    <id>" + element.getNum() + "</id>\n");
                fileWriter.write("</contact>\n");
            }
            fileWriter.write("</contacts>\n");

            fileWriter.close();
        } catch (IOException e) {
            Log.e("XmlParser", "Error al guardar contactos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
