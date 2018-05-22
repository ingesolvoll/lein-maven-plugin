package ingesolvoll;


import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Symbol;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Executes leiningen
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.COMPILE)
public class RunMojo extends AbstractMojo {

    @Parameter(property = "lein.command", defaultValue = "compile")
    private String command;

    public void execute() throws MojoExecutionException {
        Clojure.var("clojure.core", "require").invoke(Symbol.intern("ingesolvoll.lein-maven-plugin"));
        IFn lein = Clojure.var("ingesolvoll.lein-maven-plugin", "main");

        if (command == null) {
            throw new MojoExecutionException("No command to run for leiningen.");
        }
        lein.invoke(command);
    }
}