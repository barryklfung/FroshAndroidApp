package ca.skule.froshapplication.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ItemContent {
	
	/**
	 * An array of sample (dummy) items.
	 */
	public static List<TipItem> ITEMS = new ArrayList<TipItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static String[] FroshW = new String[] {
		"-  Meet new people! All of you 1T7’s are starting on a journey through Skule – get to know the people you’ll be spending your next 4 (or more) years with.                                              ",
		"-  Work hard and play hard – enjoy the F!rosh Week events with lots of spirit, and study hard for the Engineering Entrance Aptitude test – if you start off your year with a bang, Skule will become much sweeter.                                              ",
		"-  Watch your hardhat! If it gets stolen though, head over to the Engineering Society office in the basement of the Sandford Fleming Building.                                              ",
		"-  Keep your ears open – there’s more to F!rosh Week than meets the eye…                                              ",
		"-  Don’t forget to go to your classes on Thursday and Friday!                                              "
	};
	public static String[] Academic = new String[] {
		"-  Good studying starts from the beginning – pre-read or review lecture material, and you’ll grasp the material better.                                              ",
		"-  Don’t cram at the last minute! Continuous practice is the best way to understand material, and you’ll be prepared once midterms and exams roll around.                                              ",
		"-  Speaking of continuous practice, exams and midterms can be found at courses.skule.ca                                              ",
		"-  Don’t obsess over the grades: University is about the education – aim to understand the material, and don’t worry about the raw grades. The point is to keep at it.                                              ",
		"-  Attend your classes and tutorials: they teach and reinforce the concepts that you’ll be evaluated on. As well, most courses have weekly quizzes that will be marked.                                              "
	};
	public static String[] SkuleLife = new String[] {
		"-  Get involved! Extra-curricular activities will make you busy, but they will make the university experience that much sweeter. Since U of T is so big, there’s guaranteed to be something for everybody.                                              ",
		"-  In Skule, there are a multitude of design teams, athletic teams, hobby teams, and outreach teams, among others. You can find a complete list of them at http://skule.ca/clubs/                                              ",
		"-  Almost all clubs in U of T have to be affiliated with ULife – over 900 recognized campus groups can be found on their website, https://www.ulife.utoronto.ca/                                              ",
		"-  If all else fails, you can always start a club of your own: there are tens of thousands of other people in the university, and there’s bound to be somebody that shares your interests.                                              "
	};
	public static Map<String, TipItem> ITEM_MAP = new HashMap<String, TipItem>();

	static {
		// Add 3 sample items.
		addItem(new TipItem("F!rosh Week Tips", FroshW));
		addItem(new TipItem("Academic Tips", Academic));
		addItem(new TipItem("Skule Life Tips", SkuleLife));
	}

	private static void addItem(TipItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class TipItem {
		public String id;
		public String[] content;

		public TipItem(String id, String[] content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return id;
		}
	}
}
