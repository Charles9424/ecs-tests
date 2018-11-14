import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.STRING;

import MTax.XTax;
import MTax.X_Tax;

public class MTax implements Constant {
    
	
    
    
    public MTax(){
        
    }
    
    public static List<String> validate(List<X_Tax> xTaxList) {
        
        List<String> errorList = new ArrayList<>();
        List<String> validIds = new ArrayList<>();
        
        
        List<String> taxCategoryList = MInfoTaxCategory.getTaxCategoryStringList();
        
        if(xTaxList != null && xTaxList.size() > 0) {
            
            int cont = 0;
       
            if(cont<=0){
                errorList.add("Debe de incluir al menos una tasa no local");
            }    
                
        }
        return errorList;
        
    
    }
    
    private static boolean validXtaxes(List<X_Tax> XtaxList, List<String> errorList<String> validIds) {
    	boolean TaxesLocal=false;
    	for (X_Tax tax : xTaxList) {
    		if(tax.getId()!=null) {
    			validIds.add(tax.getId().toString());
    		}
    		else
    			errorList.add("El impuesto no es un dato valido");  		
    	}
    	return TaxesLocal;
    }
    
    
    
    protected static boolean XTaxListNotValid(List<X_Tax> xTaxList, List<String> errorList) {
    	boolean XTaxListNotValid=xTaxList;
    	xTaxList =null;
    	if(XTaxListNotValid) {
    		return false;
    	}
    	errorList.add("el documento no tiene tasas");
    	return true;
    }
    
    
    private static boolean TaxImport(List<String> errorList, X_Tax tax) {
    boolean TaxImport=false;
    if(tax.getAmount() == null) {
        errorList.add("El impuesto es obligatorio");
        }
    else if(tax.getTax() == null) {
    	errorList.add("El impuesto es obligatorio");
    }
    
    else TaxImport=true;
    return TaxImport;
    }
    
    private static void Import_obli(List<String> errorList, X_Tax tax) {
    	boolean Import_obli=false;
    	if(tax.isLocal()){
         if(tax.isTrasladado() && tax.getTaxAmount() == null ) {
              errorList.add("El importe es obligatorio");
          }
          } 
      else {
          if(tax.getTaxAmount() == null ) {
              errorList.add("El importe es obligatorio");
          }
      }   	
    }
    
    protected static void ValidIds(List<X_Tax> xTaxList, List<String> errorList,List<String> validIds) {
        if(validIds.size() !=0){
        List<X_Tax> xt = TaxsByListId(validIds, false);
        if(xt.size() != validIds.size()){
        errorList.add("Existen datos no guardados previamente");
        }
        else{
            HashMap<String, X_Tax> map_taxs = new HashMap<String, X_Tax>();
            for(X_Tax tax: xt){
                map_taxs.put(tax.getId().toString(), tax);
            }
            for(int i = 0; i < xTaxList.size(); i++){
                if(xTaxList.get(i).getId() != null){
                    xTaxList.get(i).setCreated(
                            map_taxs.get(xTaxList.get(i).getId().toString())
                                    .getCreated());
                }
            }
        }
    }
    }
    
}
