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
            // Configuración del parser XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            // Obtenemos la lisa de nodos del documento xml
            NodeList contactNodes = doc.getElementsByTagName("contact");

            // Obtener los valores de nombre, teléfono y ID de todos los contacto
            for (int i = 0; i < contactNodes.getLength(); i++) {
                Element contactElement = (Element) contactNodes.item(i);
                String name = contactElement.getElementsByTagName("nombre").item(0).getTextContent();
                String phone = contactElement.getElementsByTagName("tel").item(0).getTextContent();
                String id = contactElement.getElementsByTagName("id").item(0).getTextContent();

                // Crear un objeto ListElements y agregarlo a la lista
                ListElements contact = new ListElements(name, phone, id);
                elements.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devuelve la lista de contactos
        return elements;
    }

    private static void copyRawFileToInternalStorage(Context context, File file) {
        //Se obtiene un InputStream desde la carpeta donde esta el xml
        InputStream inputStream = context.getResources().openRawResource(R.raw.contacts);

        //Se crea un FileOutputStream para escribir en el archivo xml
        try {
            //Se copian los datos del InputStream al OutputStream utilizando un buffer, para que el archivo se copie correctamente.
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

    //Este método guarda la lista actualizada de contactos en el archivo xml en la memoria
    public static void saveContacts(Context context, List<ListElements> elements) {

        //Se crea un FileWriter para escribir en el archivo xml.
        try {
            File file = new File(context.getFilesDir(), "contacts.xml");
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write("<contacts>\n");

            //Se recorre la lista elements y se escribe cada contacto como un elemento en el archivo xml con los datos
            for (ListElements element : elements) {
                fileWriter.write("<contact>\n");
                fileWriter.write("    <nombre>" + element.getNombre() + "</nombre>\n");
                fileWriter.write("    <tel>" + element.getTel() + "</tel>\n");
                fileWriter.write("    <id>" + element.getNum() + "</id>\n");
                fileWriter.write("</contact>\n");
            }
            fileWriter.write("</contacts>\n");

            //se cierra el FileWriter para que los cambios se guarden
            fileWriter.close();
        } catch (IOException e) {
            Log.e("XmlParser", "Error al guardar contactos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

