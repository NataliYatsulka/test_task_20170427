package com.opinta.service;

import com.opinta.entity.Address;
import com.opinta.entity.Counterparty;
import com.opinta.entity.PostcodePool;
import com.opinta.entity.Shipment;
import com.opinta.entity.Counterparty;
import com.opinta.entity.Client;
import com.opinta.entity.DeliveryType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PDFGeneratorServiceTest {
    @Mock
    private ShipmentService shipmentService;

    private PDFGeneratorService pdfGeneratorService;
    private Shipment shipment;

    @Before
    public void setUp() throws Exception {
        pdfGeneratorService = new PDFGeneratorServiceImpl(shipmentService);

        Address senderAddress = new Address("00001", "Ternopil", "Monastiriska",
                        "Monastiriska", "Sadova", "51", "");
        Address recipientAddress = new Address("00002", "Kiev", "", "Kiev", "Khreschatik", "121", "37");
        Counterparty counterparty = new Counterparty("Modna kasta",
                new PostcodePool("00003", false));
        Client sender = new Client("FOP Ivanov", "001", senderAddress, counterparty);
        Client recipient = new Client("Petrov PP", "002", recipientAddress, counterparty);
        shipment = new Shipment(sender, recipient, DeliveryType.W2W,
                new BigDecimal("2.5"), new BigDecimal("15.25"), null);
    }

    @Test
    public void generateLabel_and_generatePostpay_ShouldReturnNotEmptyFile() {
        when(shipmentService.getEntityById(1L)).thenReturn(shipment);
        assertNotEquals("PDFGenerator returned an empty label",
                pdfGeneratorService.generateLabel(1L).length, 0);
        assertNotEquals("PDFGenerator returned an empty postpay form",
                pdfGeneratorService.generateLabel(1L).length, 0);
        verify(shipmentService, atLeast(2)).getEntityById(1L);
    }

    @Test
    public void generateLabel_ShouldReturnValidAcroForms() throws Exception {
        when(shipmentService.getEntityById(1L)).thenReturn(shipment);

        byte[] labelForm = pdfGeneratorService.generateLabel(1L);

        PDAcroForm acroForm = getAcroFormFromPdfFile(labelForm);

        PDTextField field = (PDTextField) acroForm.getField("senderName");
        assertEquals("Expected senderName form to contain FOP Ivanov",
                field.getValue(), "FOP Ivanov");

        field = (PDTextField) acroForm.getField("senderAddress");
        assertEquals("Expected senderAddress form to contain Sadova st., 51, Monastiriska\n00001",
                field.getValue(), "Sadova st., 51, Monastiriska\n00001");

        field = (PDTextField) acroForm.getField("recipientName");
        assertEquals("Expected senderName form to contain Petrov PP", field.getValue(), "Petrov PP");

        field = (PDTextField) acroForm.getField("recipientAddress");
        assertEquals("Expected recipientAddress form to contain Khreschatik st., 121, Kiev\n00002",
                field.getValue(), "Khreschatik st., 121, Kiev\n00002");

        field = (PDTextField) acroForm.getField("mass");
        assertEquals("Expected mass to be 1.0", field.getValue(), "1.0");

        field = (PDTextField) acroForm.getField("value");
        assertEquals("Expected value to be 12.5", field.getValue(), "12.5");

        field = (PDTextField) acroForm.getField("sendingCost");
        assertEquals("Expected sendingCost to be 2.5", field.getValue(), "2.5");

        field = (PDTextField) acroForm.getField("postPrice");
        assertEquals("Expected postPrice to be 15.25", field.getValue(), "15.25");

        field = (PDTextField) acroForm.getField("totalCost");
        assertEquals("Expected totalCost to be 15", field.getValue(), "15.25");

        verify(shipmentService).getEntityById(1L);
    }

    @Test
    public void generatePostpay_ShouldReturnValidAcroForms() throws Exception {
        when(shipmentService.getEntityById(1L)).thenReturn(shipment);

        byte[] postpayForm = pdfGeneratorService.generatePostpay(1L);

        PDAcroForm acroForm = getAcroFormFromPdfFile(postpayForm);

        PDTextField field = (PDTextField) acroForm.getField("senderName");
        assertEquals("Expected senderName form to contain FOP Ivanov",
                field.getValue(), "FOP Ivanov");

        field = (PDTextField) acroForm.getField("senderAddress");
        assertEquals("Expected senderAddress form to contain Sadova st., 51, Monastiriska\n00001",
                field.getValue(), "Sadova st., 51, Monastiriska\n00001");

        field = (PDTextField) acroForm.getField("recipientName");
        assertEquals("Expected senderName form to contain Petrov PP", field.getValue(), "Petrov PP");

        field = (PDTextField) acroForm.getField("recipientAddress");
        assertEquals("Expected recipientAddress form to contain Khreschatik st., 121, Kiev\n00002",
                field.getValue(), "Khreschatik st., 121, Kiev\n00002");

        field = (PDTextField) acroForm.getField("priceHryvnas");
        assertEquals("Expected priceHryvnas to be 15", field.getValue(), "15");

        field = (PDTextField) acroForm.getField("priceKopiyky");
        assertEquals("Expected priceKopiyky to be 25", field.getValue(), "25");

        verify(shipmentService).getEntityById(1L);
    }

    private PDAcroForm getAcroFormFromPdfFile(byte[] postpayForm) throws IOException {
        return PDDocument
                .load(postpayForm)
                .getDocumentCatalog()
                .getAcroForm();
    }
}
