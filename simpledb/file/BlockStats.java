package simpledb.file;
import java.util.HashMap;

public class BlockStats {
    // Variabili di istanza per tenere traccia delle operazioni sui blocchi
    private HashMap<String, Integer> statsW;
    private HashMap<String, Integer> statsR;

    // Costruttore: inizializza l'oggetto con un BlockId specifico e azzera i contatori
    public BlockStats(){
        this.statsW = new HashMap<>();
        this.statsR = new HashMap<>();
    }

    
    /** 
     * @param block
     */
    // Registra una lettura del blocco solo se il BlockId corrisponde a quello monitorato
    public void logReadBlock(BlockId block){
        this.statsR.put(block.fileName(), this.statsR.getOrDefault(block.fileName(), 0) + 1);    
    }

    // Registra una scrittura sul blocco solo se il BlockId corrisponde a quello monitorato
    public void logWriteBlock(BlockId block){
        this.statsW.put(block.fileName(), this.statsW.getOrDefault(block.fileName(), 0) + 1);
    }
    
    // Resetta le statistiche di lettura e scrittura
    public void resetStats(){
        this.statsR.clear();
        this.statsW.clear();
    }
    private int getReadsTotal(){
        return this.statsR.values().stream().mapToInt(Integer::intValue).sum();
    }
    private int getWritesTotal(){
        return this.statsW.values().stream().mapToInt(Integer::intValue).sum();
    }
    // Override del metodo toString per ottenere una rappresentazione testuale delle statistiche
    @Override
    public String toString(){
        return "Block reads: " + getReadsTotal() +
       (this.statsR.isEmpty() ? "" : " in " + this.statsR) +
       "\nBlock writes: " + getWritesTotal() +
       (this.statsW.isEmpty() ? "" : " in " + this.statsW);
    }
}
