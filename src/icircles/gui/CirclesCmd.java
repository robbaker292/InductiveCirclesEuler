package icircles.gui;


import java.util.ArrayList;
import icircles.abstractDescription.AbstractDescription;
import icircles.recomposition.Recomposer;
import icircles.recomposition.RecompositionStep;
import icircles.util.CannotDrawException;
import icircles.concreteDiagram.ConcreteDiagram;
import icircles.concreteDiagram.DiagramCreator;
import icircles.decomposition.Decomposer;
import icircles.decomposition.DecompositionStep;

public class CirclesCmd {

    private static final long serialVersionUID = 1L;
    private int decompositionStategy = 3;
    private int recompositionStategy = 2;
    private int SIZE = 200;
    boolean useColors = true;

    CirclesCmd(String input) {
    	draw(input);
        //drawTest(63);
        //draw("a b ab");
    }

    void draw(String s) {
        redraw(s);
    }
/*
    void drawTest(int test_num) {
        settingsPanel.setDecompStrategy(TestData.test_data[test_num - 1].decomp_strategy);
        settingsPanel.setRecompStrategy(TestData.test_data[test_num - 1].recomp_strategy);
        draw(TestData.test_data[test_num - 1].description);
    }

*/
    
    private void goDraw(String description, int decomp_strategy, int recomp_strategy) {

        ArrayList<DecompositionStep> d_steps = new ArrayList<DecompositionStep>();
        ArrayList<RecompositionStep> r_steps = new ArrayList<RecompositionStep>();
        ConcreteDiagram cd = null;
        String failureMessage = "";
        try {
            Decomposer d = new Decomposer(decomp_strategy);
            d_steps.addAll(d.decompose(
                    AbstractDescription.makeForTesting(description)));

            Recomposer r = new Recomposer(recomp_strategy);
            r_steps.addAll(r.recompose(d_steps));
            DiagramCreator dc = new DiagramCreator(d_steps, r_steps, SIZE);
            cd = dc.createDiagram(SIZE);
            
            System.out.println("label, cx, cy, radius,");
            for (icircles.concreteDiagram.CircleContour c: cd.getCircles()){
            	System.out.println(c + ",");
            }
            //System.out.println(cd.getCircles());
            
        } catch (CannotDrawException x) {
            failureMessage = x.message;
        }
       // resultPanel.show(description, failureMessage, cd, SIZE, useColors);
    }
    
    void redraw(String s) {
        goDraw(s,decompositionStategy,recompositionStategy);
    }

}
