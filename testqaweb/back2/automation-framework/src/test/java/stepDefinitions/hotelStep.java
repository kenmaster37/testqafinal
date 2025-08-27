package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Base_PO;
import pageObjects.Contact_Us_PO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class hotelStep {
    private String checkInDate;
    private String checkOutDate;
    private Base_PO base_po = new Base_PO(DriverFactory.getDriver());
    private Contact_Us_PO contact_us_po = new Contact_Us_PO(DriverFactory.getDriver());
    private WebDriver driver =  DriverFactory.getDriver();
    private double precioMinimo = Double.MAX_VALUE;
    private WebElement hotelEconomico = null;


    @Given("el usuario navega a la página de hoteles")
    public void i_access_the_webdriver_university_login_page() {
        base_po.navigateTo_URL("https:/demos.devexpress.com/rwa/dxhotels/");
    }


    @When("aplica los filtros de búsqueda")
    public void aplicarFiltros() throws InterruptedException {
        LocalDate hoy = LocalDate.now();
        checkInDate = hoy.plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        checkOutDate = hoy.plusDays(7).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        base_po.waitFor(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_LocationComboBox_I"));
        WebElement locationInput = driver.findElement(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_LocationComboBox_I"));
        locationInput.click();
        locationInput.clear();
        locationInput.sendKeys("London");
        locationInput.click();


        base_po.waitFor(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_CheckInDateEdit_I"));
        base_po.sendKeys(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_CheckInDateEdit_I"), checkInDate);

        base_po.waitFor(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_CheckOutDateEdit_I"));
        base_po.sendKeys(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_CheckOutDateEdit_I"), checkOutDate);

        base_po.waitFor(By.xpath("//*[@id=\"MainContentPlaceHolder_SearchPanel_SearchPanelLayout_AdultsSpinEdit_I\"][1]"));
        WebElement adultsInput = driver.findElement(By.xpath("//*[@id=\"MainContentPlaceHolder_SearchPanel_SearchPanelLayout_AdultsSpinEdit_I\"][1]"));
        adultsInput.click();
        adultsInput.sendKeys(Keys.CONTROL + "a");
        adultsInput.sendKeys(Keys.DELETE);
        adultsInput.sendKeys("3");


        base_po.waitFor(By.xpath("//*[@id=\"MainContentPlaceHolder_SearchPanel_SearchPanelLayout_ChildrenSpinEdit_I\"][1]"));
        WebElement childrenInput = driver.findElement(By.xpath("//*[@id=\"MainContentPlaceHolder_SearchPanel_SearchPanelLayout_ChildrenSpinEdit_I\"][1]"));
        childrenInput.click();
        childrenInput.sendKeys(Keys.CONTROL + "a");
        childrenInput.sendKeys(Keys.DELETE);
        childrenInput.sendKeys("2");
        base_po.waitFor(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_SearchButton_CD"));
        base_po.waitForWebElementAndClick(By.id("MainContentPlaceHolder_SearchPanel_SearchPanelLayout_SearchButton_CD"));



        base_po.waitFor(By.xpath("//*[@id=\"MainContentPlaceHolder_FilterFormLayout_NightlyRateTrackBar_MD\"][1]"));
        WebElement nightRateSlider = driver.findElement(By.xpath("//*[@id=\"MainContentPlaceHolder_FilterFormLayout_NightlyRateTrackBar_MD\"][1]"));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.clickAndHold(nightRateSlider).moveByOffset(200, 0).release().perform();


        base_po.waitForWebElementAndClick(By.id("MainContentPlaceHolder_FilterFormLayout_OurRatingCheckBoxList_RB0_I_D"));
        base_po.waitForWebElementAndClick(By.id("MainContentPlaceHolder_FilterFormLayout_OurRatingCheckBoxList_RB1_I_D"));

        base_po.waitFor(By.id("MainContentPlaceHolder_FilterFormLayout_ApplyFilterButton_CD"));
        driver.findElement(By.id("MainContentPlaceHolder_FilterFormLayout_ApplyFilterButton_CD")).click();

    }

    @And("selecciona el hotel más económico")
    public void seleccionarHotelMasEconomico() {
        // Espera a que los hoteles estén visibles después de aplicar el filtro
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dxdvItem_Metropolis")));

        List<WebElement> hoteles = driver.findElements(By.className("dxdvItem_Metropolis"));
        precioMinimo = Double.MAX_VALUE;
        hotelEconomico = null;

        for (WebElement hotel : hoteles) {
            WebElement precioElemento = hotel.findElement(By.className("price"));
            String precioTexto = precioElemento.getText().replaceAll("[^0-9.]", "").trim();
            System.out.println("Precio encontrado: " + precioTexto);
            double precio;
            try {
                precio = Double.parseDouble(precioTexto);
            } catch (NumberFormatException e) {
                continue;
            }
            if (precio < precioMinimo) {
                precioMinimo = precio;
                hotelEconomico = hotel;
            }

        }

        if (hotelEconomico != null) {
            System.out.println("Hotel más económico encontrado con precio: " + precioMinimo);
            WebElement bookItBtn = hotelEconomico.findElement(By.className("dxb"));
            bookItBtn.click();
        } else {
            throw new AssertionError("No se encontró hotel económico para reservar.");
        }
    }
    @Then("valida el mensaje de reservación exitosa")
    public void validarMensaje() {
        WebElement mensaje = driver.findElement(By.xpath("//*[contains(text(), 'Your Reservation Summary')]"));
        if (!mensaje.isDisplayed()) {
            throw new AssertionError("El mensaje de reservación exitosa no se mostró.");
        }

        String textoMensaje = mensaje.getText();
        if (!textoMensaje.contains("Your Reservation Summary")) {
            throw new AssertionError("El mensaje de reservación no es el esperado.");
        }
        driver.quit();
    }
}
