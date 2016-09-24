package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.app.model.Transaction;
import static org.junit.Assert.*;

// import com.devfactory.sf.base.insight.Transaction;
// import com.devfactory.sf.base.util.SfJsonConverter;

// import com.fasterxml.jackson.databind.type.TypeFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Test class for {@link OsService}.
 * <p>
 * Created by Anderson Araujo.
 */
public class OsServiceTest {

    private static final List<URI> TEST_INSIGHT_URIS = Arrays.asList(
            URI.create("classpath:test/insights/test_insight_valid.json"),
            URI.create("classpath:test/insights/test_insight_valid_2.json"),
            URI.create("classpath:test/insights/test_insight_invalid_level.json"),
            URI.create("classpath:test/insights/test_insight_invalid_category.json"));
    /* *
     * private static final String TEST_INSIGHT_CATEG_CONFIG =
     * "test/insights/test_all_insights_categories_config.json" ;
     * 
     * private static final List< Transaction > ALL_INSIGHTS_EXPECTED_RESULT = SfJsonConverter .
     * resourceToObject ( TEST_INSIGHT_CATEG_CONFIG , TypeFactory . defaultInstance ().
     * constructCollectionType ( List.class, Transaction .class));
     * 
     * private static final List< Transaction > NON_BETA_INSIGHTS_EXPECTED_RESULT = SfJsonConverter
     * . resourceToObject ( "test/insights/test_non_beta_insights_categories_config.json" ,
     * TypeFactory . defaultInstance (). constructCollectionType ( List.class, Transaction .class));
     * 
     * private static final List< Transaction > EXPECTED_RESULT_CATEGORIES_WRONG_ORDER =
     * SfJsonConverter . resourceToObject ( TEST_INSIGHT_CATEG_CONFIG , TypeFactory .
     * defaultInstance (). constructCollectionType ( List.class, Transaction .class));
     * 
     * private static final List< Transaction > EXPECTED_RESULT_INSIGHTS_WRONG_ORDER =
     * SfJsonConverter . resourceToObject ( TEST_INSIGHT_CATEG_CONFIG , TypeFactory .
     * defaultInstance (). constructCollectionType ( List.class, Transaction .class));
     */


    private OsService _osservice;


    @BeforeClass
    public static void setupStubs() {
        // Change the order of the categories
        /*
         * Collections.reverse(EXPECTED_RESULT_CATEGORIES_WRONG_ORDER); // Change the order of the
         * insights inside the categories EXPECTED_RESULT_INSIGHTS_WRONG_ORDER.forEach(category -> {
         * List<Transaction.Insight> insightList = new ArrayList<>(category.getInsights());
         * Collections.reverse(insightList); LinkedHashSet<Transaction.Insight> insightSet = new
         * LinkedHashSet<>(insightList); category.setInsights(insightSet); });
         */
    }

    @Before
    public void setup() throws MalformedURLException, URISyntaxException {
        // _osservice = new OsService(TEST_INSIGHT_URIS, true);
    }


    @Test
    public void testNominalListAllInsightsCategories() throws Exception {
        // Assert both lists have the same content (regardless the order)
        // assertEquals(ALL_INSIGHTS_EXPECTED_RESULT, _osservice.getAllInsightsCategoryList());
        // Assert Insight lists have the same order
        // assertTrue("Insight lists should have the same order.", areInsightsAreInSameOrder(
        // ALL_INSIGHTS_EXPECTED_RESULT, _osservice.getAllInsightsCategoryList()));
    }

    @Test
    public void testNominalListNonBetaInsightsCategories() throws Exception {
        // Assert both lists have the same content (regardless the order)
        /*
         * assertEquals(NON_BETA_INSIGHTS_EXPECTED_RESULT,
         * _osservice.getNonBetaInsightsCategoryList()); // Assert Insight lists have the same order
         * assertTrue("Insight lists should have the same order.", areInsightsAreInSameOrder(
         * NON_BETA_INSIGHTS_EXPECTED_RESULT, _osservice.getNonBetaInsightsCategoryList()));
         */
    }

    @Test
    public void testNominalListCategoriesHasCorrectOrder() throws Exception {
        // assertNotEquals(EXPECTED_RESULT_CATEGORIES_WRONG_ORDER,
        // _osservice.getAllInsightsCategoryList());
    }

    @Test
    public void testNominalListCategoriesInsightHasCorrectOrder() throws Exception {
        // Assert both lists have the same content (regardless the order)
        /*
         * assertEquals(EXPECTED_RESULT_INSIGHTS_WRONG_ORDER,
         * _osservice.getAllInsightsCategoryList()); // Assert Insight lists have different order
         * assertFalse("Insight lists should have different order.", areInsightsAreInSameOrder(
         * EXPECTED_RESULT_INSIGHTS_WRONG_ORDER, _osservice.getAllInsightsCategoryList()));
         */
    }

    /**
     * Checks whether the Insight lists inside each Insight Category have the same order
     *
     * @param expectedCategoryList The expected category list
     * @param actualCategoryList The actual category list
     * @return true if all Insight lists inside each Insight Category have the same order. False
     *         otherwise.
     */
    private static boolean areInsightsAreInSameOrder(List<Transaction> expectedCategoryList,
            List<Transaction> actualCategoryList) {
        /*
         * int idx = 0; for (Transaction insightCategoryWrongOrder : expectedCategoryList) {
         * List<Transaction.Insight> expectedInsights = new
         * ArrayList<>(insightCategoryWrongOrder.getInsights()); List<Transaction.Insight>
         * actualInsights = new ArrayList<>(actualCategoryList.get(idx).getInsights()); // Comparing
         * as Lists, because it takes into account the order of the elements if
         * (!expectedInsights.equals(actualInsights)) { return false; } idx++; }
         */
        // If reach this point, it means that all insight lists have the same order
        return true;
    }

}
