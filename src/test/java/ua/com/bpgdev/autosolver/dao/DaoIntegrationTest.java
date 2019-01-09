package ua.com.bpgdev.autosolver.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.com.bpgdev.autosolver.entity.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings("OptionalGetWithoutIsPresent")
public class DaoIntegrationTest {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private VehicleMarkDao vehicleMarkDao;
    @Autowired
    private VehicleModelDao vehicleModelDao;
    @Autowired
    private BodyStyleDao bodyStyleDao;
    @Autowired
    private DriveTypeDao driveTypeDao;
    @Autowired
    private GearBoxDao gearBoxDao;

    @Test
    @Transactional
    public void categoryITest() {
        String expectedCategoryName = "categoryTest";
        int expectedCategoryValue = 10000;
        int expectedCategoryCount = 3;
        List<Category> actualCategories = new ArrayList<>();
        Category expectedCategory = new Category(expectedCategoryName, expectedCategoryValue);
        Category actualCategory;

        categoryDao.save(expectedCategory);
        actualCategory = categoryDao.findByValue(expectedCategoryValue);
        categoryDao.findAll().forEach(actualCategories::add);
        int actualCategoryCount = actualCategories.size();

        assertEquals(expectedCategoryCount, actualCategoryCount);
        assertEquals(expectedCategory, actualCategory);

        actualCategory = categoryDao.findByName("Легковые");
        expectedCategory = new Category(1000L, "Легковые", 1);

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    @Transactional
    public void vehicleMarkITest() {
        String expectedVehicleMarkName = "vehicleMarkTest";
        int expectedVehicleMarkValue = 1000;
        Long categoryId = 1000L;
        Category category = categoryDao.findById(categoryId).get();
        int expectedVehicleMarkCount = 4;
        List<VehicleMark> actualVehicleMarks = new ArrayList<>();
        VehicleMark expectedVehicleMark = new VehicleMark(category, expectedVehicleMarkName, expectedVehicleMarkValue);
        VehicleMark actualVehicleMark;

        vehicleMarkDao.save(expectedVehicleMark);
        actualVehicleMark = vehicleMarkDao.findByCategoryIdAndValue(categoryId, expectedVehicleMarkValue);
        vehicleMarkDao.findAll().forEach(actualVehicleMarks::add);
        int actualVehicleMarkCount = actualVehicleMarks.size();

        assertEquals(expectedVehicleMarkCount, actualVehicleMarkCount);
        assertEquals(expectedVehicleMark, actualVehicleMark);

        actualVehicleMark = vehicleMarkDao.findByCategoryIdAndName(categoryId, expectedVehicleMarkName);
        assertEquals(expectedVehicleMark, actualVehicleMark);

        actualVehicleMark = vehicleMarkDao.findByCategoryAndValue(category, expectedVehicleMarkValue);
        assertEquals(expectedVehicleMark, actualVehicleMark);

        actualVehicleMark = vehicleMarkDao.findByCategoryAndName(category, expectedVehicleMarkName);
        assertEquals(expectedVehicleMark, actualVehicleMark);

        actualVehicleMark = vehicleMarkDao.findByCategoryIdAndValue(categoryId, 79);
        expectedVehicleMark = new VehicleMark(2000L, category, "Toyota", 79);
        assertEquals(expectedVehicleMark, actualVehicleMark);

        actualVehicleMarks = vehicleMarkDao.findByCategoryId(1000L);
        actualVehicleMarkCount = actualVehicleMarks.size();
        expectedVehicleMarkCount = 3;
        assertEquals(expectedVehicleMarkCount, actualVehicleMarkCount);
    }

    @Test
    @Transactional
    public void vehicleModelITest() {
        String expectedVehicleModelName = "vehicleModelTest";
        int expectedVehicleModelValue = 1000;
        Long vehicleMarkId = 2000L;
        VehicleMark vehicleMark = vehicleMarkDao.findById(vehicleMarkId).get();
        int expectedVehicleModelCount = 4;
        List<VehicleModel> actualVehicleModels = new ArrayList<>();
        VehicleModel expectedVehicleModel = new VehicleModel(vehicleMark, expectedVehicleModelName, expectedVehicleModelValue);
        VehicleModel actualVehicleModel;

        vehicleModelDao.save(expectedVehicleModel);
        actualVehicleModel = vehicleModelDao.findByVehicleMarkIdAndValue(2000L, expectedVehicleModelValue);
        vehicleModelDao.findAll().forEach(actualVehicleModels::add);
        int actualVehicleModelCount = actualVehicleModels.size();

        assertEquals(expectedVehicleModelCount, actualVehicleModelCount);
        assertEquals(expectedVehicleModel, actualVehicleModel);

        expectedVehicleModelCount = 3;
        actualVehicleModels = vehicleModelDao.findByVehicleMarkId(2000L);
        actualVehicleModelCount = actualVehicleModels.size();

        assertEquals(expectedVehicleModelCount, actualVehicleModelCount);
    }

    @Test
    @Transactional
    public void bodyStyleITest() {
        String expectedBodyStyleName = "bodyStyleTest";
        int expectedBodyStyleValue = 1000;
        Long categoryId = 2000L;
        Category category = categoryDao.findById(categoryId).get();
        int expectedBodyStyleCount = 4;
        List<BodyStyle> actualBodyStyles = new ArrayList<>();
        BodyStyle expectedBodyStyle = new BodyStyle(category, expectedBodyStyleName, expectedBodyStyleValue);
        BodyStyle actualBodyStyle;

        bodyStyleDao.save(expectedBodyStyle);
        actualBodyStyle = bodyStyleDao.findByCategoryIdAndValue(categoryId, expectedBodyStyleValue);
        bodyStyleDao.findAll().forEach(actualBodyStyles::add);
        int actualBodyStyleCount = actualBodyStyles.size();

        assertEquals(expectedBodyStyleCount, actualBodyStyleCount);
        assertEquals(expectedBodyStyle, actualBodyStyle);
    }

    @Test
    @Transactional
    public void driveTypeITest() {
        String expectedDriveTypeName = "driveTypeTest";
        int expectedDriveTypeValue = 1000;
        Long categoryId = 2000L;
        Category category = categoryDao.findById(categoryId).get();
        int expectedDriveTypeCount = 4;
        List<DriveType> actualDriveTypes = new ArrayList<>();
        DriveType expectedDriveType = new DriveType(category, expectedDriveTypeName, expectedDriveTypeValue);
        DriveType actualDriveType;

        driveTypeDao.save(expectedDriveType);
        actualDriveType = driveTypeDao.findByCategoryIdAndValue(categoryId, expectedDriveTypeValue);
        driveTypeDao.findAll().forEach(actualDriveTypes::add);
        int actualBodyStyleCount = actualDriveTypes.size();

        assertEquals(expectedDriveTypeCount, actualBodyStyleCount);
        assertEquals(expectedDriveType, actualDriveType);
    }

    @Test
    @Transactional
    public void gearBoxITest() {
        String expectedGearBoxName = "gearBoxTest";
        int expectedDriveTypeValue = 1000;
        Long categoryId = 2000L;
        Category category = categoryDao.findById(categoryId).get();
        int expectedGearBoxCount = 4;
        List<GearBox> actualGearBoxes = new ArrayList<>();
        GearBox expectedGearBox = new GearBox(category, expectedGearBoxName, expectedDriveTypeValue);
        GearBox actualGearBox;

        gearBoxDao.save(expectedGearBox);
        actualGearBox = gearBoxDao.findByCategoryIdAndValue(categoryId, expectedDriveTypeValue);
        gearBoxDao.findAll().forEach(actualGearBoxes::add);
        int actualGearBoxCount = actualGearBoxes.size();

        assertEquals(expectedGearBoxCount, actualGearBoxCount);
        assertEquals(expectedGearBox, actualGearBox);
    }
}