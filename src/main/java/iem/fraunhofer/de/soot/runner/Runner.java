package iem.fraunhofer.de.soot.runner;

import com.google.common.collect.Lists;
import soot.G;
import soot.Scene;
import soot.options.Options;

import java.util.LinkedList;
import java.util.List;

public class Runner
{
    final boolean setWholeProgram = true;
    private String _processDir;

    public Runner(String processDir)
    {
        _processDir = processDir;
    }

    public void ConfigureSoot(boolean includeJavaClasses)
    {
        G.v().reset();
        Options.v().set_whole_program(setWholeProgram);
        Options.v().setPhaseOption("jb", "use-original-names:true");

        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_no_bodies_for_excluded(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_keep_line_number(true);
        Options.v().set_full_resolver(true);

        if (_processDir != null){
            Options.v().set_process_dir(Lists.newArrayList(_processDir));
            Options.v().set_soot_classpath("D:\\Java\\openjdk-11.0.5_10\\lib\\jrt-fs.jar");
        }

        if (includeJavaClasses)
            Options.v().set_include(getIncludeList());
    }


    public void LoadClasses()
    {
        try
        {
            Scene.v().loadNecessaryClasses();
            System.out.println(Scene.v().getSootClassPath());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private List<String> getIncludeList()
    {
        List<String> includeList = new LinkedList<>();
        includeList.add("java.lang.AbstractStringBuilder");
        includeList.add("java.lang.Boolean");
        includeList.add("java.lang.Byte");
        includeList.add("java.lang.Class");
        includeList.add("java.lang.Integer");
        includeList.add("java.lang.Long");
        includeList.add("java.lang.Object");
        includeList.add("java.lang.String");
        includeList.add("java.lang.StringCoding");
        includeList.add("java.lang.StringIndexOutOfBoundsException");
        return includeList;
    }

    public static void main(String[] args) {
        Runner runner = new Runner("HelloWorldJava.jar");
        runner.ConfigureSoot(false);
        runner.LoadClasses();
    }
}
