import org.apache.jena.rdf.model.*;

/**
 * JANCOKED BY BERNARD
 */


public class RDF {
    public static void main(String[] args) {
        String personURI = "http://somewhere/person#john";
        String genidURI = "genid:A30163";
        String namespace = "http://somewhere/person#";
        String givenName = "John";
        String familyName = "Smith";
        String nickName = "JS";
        String fullName = givenName+" "+familyName;

        Model model = ModelFactory.createDefaultModel();

        Property nsName = model.createProperty(namespace+"Name");
        Property nsGiven = model.createProperty(namespace+"Given");
        Property nsFamily = model.createProperty(namespace+"Family");
        Property nsNickName = model.createProperty(namespace+"Nickname");
        Property nsFullName = model.createProperty(namespace+"Fullname");

        Resource johnSmith =  model.createResource(personURI).addProperty(nsFullName, fullName)
                .addProperty(nsNickName, nickName)
                .addProperty(nsName, model.createResource(genidURI))
                .addProperty(nsGiven, givenName)
                .addProperty(nsFamily, familyName);

        model.write(System.out);






    }
}
