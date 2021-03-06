//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GuiApp {

	static int year = 2964;
	static int day = 1;
	static int month =1;

	int startYear;
	int startMonth;
	int startDay;
	
	String nation;
	
	//Note: Typically the main method will be in a
	//separate class. As this is a simple one class
	//example it's all in the one class.
	//    public static void main(String[] args) {
	//        
	//        new GuiApp();
	//    }

	NationData nationData;
	fileHandler filehandler;
	JComboBox dropDownNations;
	String[] listOfNations;
	
	public GuiApp(fileHandler filehandler, final LinkedList<weather> listOfWeather, int startYear, int startMonth, int startDay, String nation)
	{
		this.filehandler = filehandler;
		this.nation = nation;
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.startDay = startDay;
		
		JFrame guiFrame = new JFrame();

		//make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Eon V�dergenerator");
		guiFrame.setSize(475,350);

		//This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);

		//Options for the JComboBox 
		listOfNations = filehandler.getListOfNations();
		String[] dates = {"1","2","3","4","5","6","7",
				"8","9","10","11","12","13","14",
				"15","16","17","18","19","20","21",
				"22","23","24","25","26","27","28"};
		String[] months = {"1","2","3","4","5","6","7",
				"8","9","10","11","12"};


		final JPanel secondPanel = new JPanel();
		//        final JPanel second = new JPanel();
		JLabel dayLabel = new JLabel("Day");
		JLabel monthLabel = new JLabel("Month");
		JComboBox date = new JComboBox(dates);
//		JComboBox month = new JComboBox(months);

		//Options for the JList
		//Options for the JList
		String[] vegOptions = {"Asparagus", "Beans", "Broccoli", "Cabbage"
				, "Carrot", "Celery", "Cucumber", "Leek", "Mushroom"
				, "Pepper", "Radish", "Shallot", "Spinach", "Swede"
				, "Turnip"};

		//The first JPanel contains a JLabel and JCombobox
		final JPanel firstPanel = new JPanel();
		JLabel area = new JLabel("Omr�de:");
		dropDownNations = new JComboBox(listOfNations);
		
		firstPanel.add(area);
		firstPanel.add(dropDownNations);
		//		firstPanel.add(dayLabel);
		//		firstPanel.add(date);


		/*l�gger till sj�lva etiketten*/
		secondPanel.add(dayLabel);
		/*l�gger till listan med alternativ*/
//		secondPanel.add(date);
//		secondPanel.add(monthLabel);
//		secondPanel.add(month);

		//Create the second JPanel. Add a JLabel and JList and
		//make use the JPanel is not visible.
		final JPanel secondPagePanel = new JPanel();
		secondPagePanel.setVisible(false);
		JLabel listLbl = new JLabel("Vegetables:");
		JList vegs = new JList(vegOptions);
		vegs.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		secondPagePanel.add(listLbl);
		secondPagePanel.add(vegs);

		JButton vegFruitBut = new JButton( "Generate Weather");
		JButton yearUp = new JButton("+");
		JButton yearDown = new JButton("-");
		JButton dayUp = new JButton("+");
		JButton dayDown = new JButton("-");
		JButton monthUp = new JButton("+");
		JButton monthDown = new JButton("-");
		
//		year = 2964;
		year = startYear;
		month = startMonth;
		day = startDay;
		
		final TextField displayYear = new TextField(String.valueOf(year));
		final TextField displayMonth = new TextField(String.valueOf(month));
		final TextField displayDay = new TextField(String.valueOf(day));

		final TextField weatherData = new TextField("                                                 ");
		
		dropDownNations.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateWeather(listOfWeather, weatherData);
			}
		}
		);
		
		yearUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateYear(1);
				updateDisplays(displayYear, displayMonth, displayDay);
				updateWeather(listOfWeather, weatherData);
			}
		});

		yearDown.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateYear(-1);
				updateDisplays(displayYear, displayMonth, displayDay);
				updateWeather(listOfWeather, weatherData);
			}
		});

		dayUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateDay(1);
				updateDisplays(displayYear, displayMonth, displayDay);
				updateWeather(listOfWeather, weatherData);
			}
		});
		dayDown.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateDay(-1);
				updateDisplays(displayYear, displayMonth, displayDay);
				updateWeather(listOfWeather, weatherData);
			}
		});

		monthUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateMonth(1);
				updateDisplays(displayYear, displayMonth, displayDay);
				updateWeather(listOfWeather, weatherData);
			}
		});
		monthDown.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				updateMonth(-1);
				updateDisplays(displayYear, displayMonth, displayDay);
				updateWeather(listOfWeather, weatherData);
			}
		});
		
		//The ActionListener class is used to handle the
		//event that happens when the user clicks the button.
		//As there is not a lot that needs to happen we can 
		//define an anonymous inner class to make the code simpler.
		vegFruitBut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				//When the fruit of veg button is pressed
				//the setVisible value of the listPanel and
				//comboPanel is switched from true to 
				//value or vice versa.
				secondPagePanel.setVisible(!secondPagePanel.isVisible());

				firstPanel.setVisible(!firstPanel.isVisible());
				secondPanel.setVisible(!secondPanel.isVisible());
			}

		});

		JPanel dayPanel = new JPanel();
		dayPanel.add(dayDown);
		dayPanel.add(displayDay);
		dayPanel.add(dayUp);
		
		secondPanel.add(dayPanel);
		
		JPanel monthPanel = new JPanel();
		monthPanel.add(monthDown);
		monthPanel.add(displayMonth);
		monthPanel.add(monthUp);
		
		secondPanel.add(monthPanel);
		
		JPanel yearPanel = new JPanel();
		yearPanel.add(yearDown);
		yearPanel.add(displayYear);
		yearPanel.add(yearUp);

		secondPanel.add(yearPanel);
//		secondPanel.add(weatherData);
		
		JPanel centerPanel = new JPanel();

		JPanel southPanel = new JPanel();
		
		centerPanel.add(secondPanel, BorderLayout.CENTER);
		centerPanel.add(secondPagePanel, BorderLayout.CENTER);

		
		//The JFrame uses the BorderLayout layout manager.
		//Put the two JPanels and JButton in different areas.
		guiFrame.add(firstPanel, BorderLayout.NORTH);
		//		guiFrame.add(secondPanel, BorderLayout.CENTER);
		//		guiFrame.add(secondPagePanel, BorderLayout.CENTER);
		guiFrame.add(centerPanel, BorderLayout.CENTER);
//		guiFrame.add(vegFruitBut,BorderLayout.SOUTH);
//		guiFrame.add(data,BorderLayout.SOUTH);
		
		
		southPanel.add(weatherData);
		guiFrame.add(southPanel,BorderLayout.SOUTH);
		
		secondPanel.setVisible(true);

		//make sure the JFrame is visible
		guiFrame.setVisible(true);
	}

	weatherCalculator newCalc = new weatherCalculator(new Random());
	
	/**
	 * updates the weather to the current weather of the day
	 * @param weather
	 * @param data
	 */
	public void updateWeather(LinkedList<weather> weather, TextField data){
		nation = listOfNations[dropDownNations.getSelectedIndex()];
		
		int seed = (28*12*(year)+28*(month)+(day))*(1+dropDownNations.getSelectedIndex());
		newCalc.setSeed(seed);
		
		nationData = filehandler.getNation(nation);
		
		System.out.println(nationData.getName());
		
		weather test = newCalc.getWeather(year, month, day, nationData);
		
		System.out.println(nationData.getTemperature(month) + "  " + test.getTemperature());
		
		String text = "Temp: " + test.getTemperature() + "   Vind: "+test.getWindStrength() + "   Regn: " +test.getRain() + "   �vrigt: " + test.getOther(); 
		
//		System.out.println("gets the weather for year " + year);
		
		data.setText(text);
	}

	public void updateDay(int in){
		day += in;
		if(day > 28){
			day -= 28;
			updateMonth(1);
		}
		else if(day < 1){
			day += 28;
			updateMonth(-1);
		}
	}

	public void updateMonth(int in){
		month += in;
		if(month > 12){
			month -= 12;
			updateYear(1);
		}
		else if(month < 1){
			month += 12;
			updateYear(-1);
		}
	}

	public void updateYear(int in){
		year += in;
	}

	private void updateDisplays(TextField displayYear, TextField displayMonth, TextField displayDay) {
		// TODO Auto-generated method stub
		displayYear.setText(String.valueOf(year));
		displayMonth.setText(String.valueOf(month));
		displayDay.setText(String.valueOf(day));
	}
}