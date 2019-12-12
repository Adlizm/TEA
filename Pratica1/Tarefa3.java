package tarefa3;

import java.io.IOException;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author graduacao
 */
public class Tarefa3 {
    public static class VizinhosMapper extends Mapper<Object, Text, Text, Text> {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            InputSplit is = context.getInputSplit();
            FileSplit f = (FileSplit) is;
            String filePath = f.getPath().toString();
            
            String[] pathSeparado = filePath.split("/");
            String nomeArquivo = pathSeparado[pathSeparado.length - 1];
            
            String texto = value.toString();
            char letra;
            int index = 0;
            int nNumbers = 0;
            char[] id = new char[17];
            letra = texto.charAt(index);
            while(letra != '\0'){
                if(letra >= '0' && letra <= '9'){
                    id[nNumbers] = letra;
                    nNumbers++;
                }else{
                    nNumbers = 0;
                    id[nNumbers] = '\0';
                }
                if(nNumbers == 16){
                    id[nNumbers] = '\0';
                    String idVizinho = new String(id);
                    context.write(new Text(nomeArquivo),new Text(idVizinho));
                    nNumbers = 0;
                }
                index++;
                letra = texto.charAt(index);
            }
        }
      }

  // REDUCE
  public static class CriaListaVizinhosReducer extends Reducer<Text,Text,Text,Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      String result = "";
      for (Text val : values) {
        result += val.toString() + " ";
      }
      context.write(key, new Text(result));
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Tarefa3");
    job.setJarByClass(Tarefa3.class);

    job.setMapperClass(VizinhosMapper.class); // MAP
    job.setReducerClass(CriaListaVizinhosReducer.class);  // REDUCE
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
    
  }
    
}
