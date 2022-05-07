package hospital;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Patient> patients = readPatientsFromXml("src/main/resources/patients.xml");

        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listPatients(patients);
                case 2 -> addNewPatient(patients);
                case 3 -> modifyPatients(patients);
                case 4 -> deletePatient(patients);
            }
            System.out.println("1 - List patients\r\n2 - Add new patient\r\n"
                    + "3 - Modify a patient\r\n4 - Delete a patient\r\n0 - Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
        }

        savePatientsToXml(patients, "src/main/resources/patients.xml");
    }
    public static ArrayList<Patient> readPatientsFromXml(String filepath) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            NodeList childNodesList = rootElement.getChildNodes();

            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", birthYear = "", address = "", disease = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfUserTag.item(j).getTextContent();
                                case "birthYear" -> birthYear = childNodesOfUserTag.item(j).getTextContent();
                                case "address" -> address = childNodesOfUserTag.item(j).getTextContent();
                                case "disease" -> disease = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    patients.add(new Patient(name, Integer.parseInt(birthYear), address,
                            Disease.valueOf(disease)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    private static void modifyPatients(ArrayList<Patient> patients) {
        System.out.print("Enter name of patient who you want to modify: ");
        String name = scanner.nextLine();
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                int birthYear = inputBirthYear();
                System.out.print("Enter address of new patient: ");
                String address = scanner.nextLine();
                Disease disease = inputDisease();
                patients.set(patients.indexOf(patient), new Patient(name, birthYear, address, disease));
                System.out.println("Patient is successfully modified.");
                return;
            }
        }
        System.out.println("There is no patient with this name.");
    }

    private static void deletePatient(ArrayList<Patient> patients) {
        System.out.print("Enter name of patient who you want to delete: ");
        String name = scanner.nextLine();
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                patients.remove(patient);
                System.out.println("Patient is successfully deleted.");
                return;
            }
        }
        System.out.println("There is no patient with this name.");
    }

    private static void addNewPatient(ArrayList<Patient> patients) {
        System.out.print("Enter name of new patient: ");
        String name = scanner.nextLine();
        int birthYear = inputBirthYear();
        System.out.print("Enter address of new patient: ");
        String address = scanner.nextLine();
        Disease disease = inputDisease();
        patients.add(new Patient(name, birthYear, address, disease));
    }

    private static int inputBirthYear() {
        int birthYear = 0;
        while (1900 > birthYear || 2022 < birthYear) {
            try {
                System.out.print("Enter birth year of new patient: ");
                birthYear = scanner.nextInt();
                scanner.nextLine();
                if (birthYear < 1900 || birthYear > 2022) {
                    System.out.println("Birth year cannot be less than" +
                            " 1900 or greater 2022.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid birth year.");
                scanner.nextLine();
            }
        }
        return birthYear;
    }

    private static Disease inputDisease() {
        Disease disease = Disease.ANXIETY;
        String diseaseString = "";
        while (diseaseString.isEmpty()) {
            try {
                System.out.print("Enter disease of new patient(use '_' instead of -,:,' and space): ");
                diseaseString = scanner.nextLine();
                disease = Disease.valueOf(diseaseString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Not valid disease.");
                diseaseString = "";
            }
        }
        return disease;
    }

    private static void listPatients(ArrayList<Patient> patients) {
        System.out.println(patients);
    }

    public static void savePatientsToXml(ArrayList<Patient> patients, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("patients");
            document.appendChild(rootElement);

            for (Patient patient : patients) {
                Element userElement = document.createElement("patient");
                rootElement.appendChild(userElement);
                createChildElement(document, userElement, "name", patient.getName());
                createChildElement(document, userElement, "birthYear",
                        String.valueOf(patient.getBirthYear()));
                createChildElement(document, userElement, "address", patient.getAddress());
                createChildElement(document, userElement, "disease", patient.getDisease().toString());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

}