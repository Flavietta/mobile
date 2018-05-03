/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entity.Soins;
import com.mycompany.services.ServicesSoins;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author majid
 */
public class StatDislike {
     public Resources theme;
    
    
    
    
    
      private DefaultRenderer buildCategoryRenderer(List<Integer> colors) {
          DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, List<Integer> values,List<Soins> So) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add(So.get(k).getTitre(), value);
            k++;
        }

        return series;
    }

    public Form createPieChartForm() {
        int m=0;
        int[] colorsss = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN,ColorUtil.LTGRAY,ColorUtil.BLACK};
        
        // Generate the values
        List<Integer> values = new ArrayList<Integer>();
         List<Integer> colors = new ArrayList<Integer>();
        ServicesSoins ss=new ServicesSoins();
        for(Soins S :ss.getMesSoins()){
        
        values.add(ss.getNbDislike(S.getRefs()));
        colors.add(colorsss[m]);
        m++;
        }
        // Set up the renderer
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Dislike Stats", values,ss.getMesSoins()), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Dislike Stats");
        Toolbar tb = f.getToolbar();
        
        tb.setTitleCentered(false);
        theme = UIManager.initFirstTheme("/theme_2");
         tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new afficheSoins(theme).show();
        });
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
        return f;
    }
    
}
