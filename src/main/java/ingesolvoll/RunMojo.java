package ingesolvoll;


import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Symbol;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Arrays;

/**
 * Executes leiningen
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.COMPILE)
public class RunMojo extends AbstractMojo {

    @Parameter(property = "lein.command", defaultValue = "compile")
    private String command;

    public void execute() throws MojoExecutionException {
        Clojure.var("clojure.core", "require").invoke(Symbol.intern("leiningen.core.main"));
        IFn lein = Clojure.var("leiningen.core.main", "-main");
        if (command == null) {
            throw new MojoExecutionException("No command to run for leiningen.");
        }
        Seq argsSeq = new Seq(Arrays.asList(command.split(" ")));
        lein.applyTo(argsSeq);
    }
}