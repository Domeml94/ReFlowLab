package de.dominikemmel.reflowlab.controller.costanalysistool;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class SpiderPlot extends JPanel{

	public JFreeChart spiderPlot (ArrayList<ObjSpiderWebData> DataList) {


		//create Data:
		CategoryDataset dataset = createDataset(DataList);
	    // Create chart:
		SpiderWebPlot plot = new SpiderWebPlot(dataset);

		JFreeChart chart = new JFreeChart("", TextTitle.DEFAULT_FONT, plot, true);


		return chart;

	}

	 private CategoryDataset createDataset(ArrayList<ObjSpiderWebData> DataList)  {

			String name1 = "total";
			String name2 = "power";
			String name3 = "electrolyte";
			String name4 = "NPV";

			DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
			for (int i = 0; i < DataList.size(); i++) {

				defaultcategorydataset.addValue(DataList.get(i).cTotal.doubleValue(), i+", " +DataList.get(i).title.get(), name1);
				defaultcategorydataset.addValue(DataList.get(i).cPower.doubleValue(), i+", " +DataList.get(i).title.get(), name2);
				defaultcategorydataset.addValue(DataList.get(i).cElectrolyte.doubleValue(), i+", " +DataList.get(i).title.get(), name3);
				defaultcategorydataset.addValue(DataList.get(i).cNPV.doubleValue(), i+", " +DataList.get(i).title.get(), name4);
			}

		 return defaultcategorydataset;

	 }
}
