package model.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.junit.runners.JUnit4;

// @RunWith(JUnit4.class)
public class CardTest {
    private Logger LOG = Logger.getLogger("model.cards.CardTest");
    
    private Card c1, c2, c2Bis, c3, c4;
    private List<Card> cardSamples;
    private List<String> cardSamplesValues;

    @Before
    public void beforeEachTest() {
        LOG.info("@Before starts");
		c1 = new Card(Rank._2, Suit.CARREAU);
		c2 = new Card(Rank._ROI, Suit.CARREAU);
		c3 = new Card(Rank._2, Suit.CARREAU);
		c4 = new Card(Rank._2, Suit.PIQUE);
		c2Bis = c2;
        cardSamples = new ArrayList<>(Arrays.asList(c1, c2, c2Bis, c3, c4));
        cardSamplesValues = new ArrayList<>(Arrays.asList(
            "2-Carreau",
                "Roi-Carreau",
                "Roi-Carreau", // c2Bis
                "2-Carreau",
                "2-Pique"
            ));
    }

    @After
    public void afterEachTest() {
        LOG.info("@After starts");
		c1 = null;
		c2 = null;
		c3 = null;
		c4 = null;
		c2Bis = null;
    }

    @Test
    public void jUnitAssertionsShouldWork()
    {
        assertTrue( true );
    }

    @Test
    public void whenCreated_CardDetails_ShouldBe_Obfuscated()
    {
        LOG.info("@Test whenCreated_CardDetails_ShouldBe_Obfuscated() starts");
        String expectedHiddenValue = "?-?";
        for (Card card : cardSamples) {
            String result = card.toString(); // method being tested
            assertTrue( expectedHiddenValue.equals(result) );
        }
    }

    @Test
    public void whenRevealed_CardDetails_ShouldBe_Readable_thenWhenHidden_Obfuscated()
    {
        LOG.info("@Test whenRevealed_CardDetails_ShouldBe_Readable_thenWhenHidden_Obfuscated() starts");

        String expectedHiddenValue = "?-?";

        for (int i = 0; i < cardSamples.size(); i++) {
            Card card = cardSamples.get(i);

            // method being tested
            card.reveale();

            String expectedRevealedValue = cardSamplesValues.get(i);
            String revealedResult = card.toString();
            // assertTrue( 
            //     "Revealed cards' toString() should display their value, in expected format :\n"
            //     + "expected: [" + expectedRevealedValue + "]\n"
            //     + "actual: [" + revealedResult + "]"
            //     , 
            //     expectedRevealedValue.equals(revealedResult) 
            // );
            assertEquals(
                "Revealed cards' toString() should display their value, in expected format", 
                expectedRevealedValue, 
                revealedResult);
            assertTrue( 
                "Revealed cards' isRevealed() should return true", 
                card.isRevealed() == true 
            );
            
            // testing a 2nd method in same test suite
            card.hide();

            String hiddenResult = card.toString();
            assertEquals( 
                "Hidden cards' toString() should obfuscate their value", 
                expectedHiddenValue,
                hiddenResult
            );
            assertTrue( 
                "Hidden cards' isRevealed() should return false", 
                card.isRevealed() == true 
            );
        }
    }
}
