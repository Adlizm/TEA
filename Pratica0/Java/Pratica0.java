import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Pratica0 {
  private final static boolean three = false;
  // MAP
  public static class MapperLike extends Mapper<Object, Text, Text, IntWritable> {

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
      String phrase = value.toString();
      String[] separateText = phrase.split(",");
      int userId = Integer.parseInt(separateText[0]);
      int movieId = Integer.parseInt(separateText[1])
      Double grade = Integer.parseInt(separateText[2]);
      if(grade >= 4){
  		context.write(new Text("" + userId),new IntWritable(movieId));
      }
    }
  }

  // REDUCE
  public static class ReduceCreateList extends Reducer<IntWritable,IntWritable,Text,Text> {

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      String  result = "";
      for (IntWritable val : values) {
      	int movieId = Integer.parseInt(val);
      	result += movieId + " "
      }
      context.write(key,new Text(result));
    }
  }

  public static class MapperCreateCombinations extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
                  
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
      String phrase = value.toString();
      String[] separateText = phrase.split(" "); //[userId, movie_0 , movie_1,...]
      
      int[] moviesIDs = new int[separateText.length - 1];
      for(int i = 1; i < separateText.length; i++)
        moviesIDs[i-1] = Integer.parseInt(separateText[i]);
      
      Arrays.sort(moviesIDs);
      
      String text;
      if(three){
  		for(int i = 0    ; i < moviesIDs.length - 2; i++){
		  for(int j = i + 1; j < moviesIDs.length - 1; j++){
      		for(int k = j + 1; k < moviesIDs.length    ; k++){
      			text = moviesIDs[i] + " " + moviesIDs[j] + " " + moviesIDs[k];
      			context.write(new Text(text),one);
      		}
      	  }
      	}
      }else{
      	for(int i = 0    ; i < moviesIDs.length - 1; i++){
		  for(int j = i + 1; j < moviesIDs.length    ; j++){
	      	text = moviesIDs[i] + " " + moviesIDs[j]; 
	   	  	context.write(new Text(text),one);
	   	  }
	   	}
      }
      
    }
  }

  // REDUCE
  public static class ReduceCount extends Reducer<IntWritable,IntWritable,Text,Text> {

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int  result = 0;
      for (IntWritable val : values) {
        result += val.get();
      }
      context.write(key,new Text(result));
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "pratica0");
    job.setJarByClass(Pratica0.class);

    job.setMapperClass(MapperLike.class); // MAP
    job.setReducerClass(ReduceCreateList.class);  // REDUCE

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

   	job.waitForCompletion(true);

    job = Job.getInstance(conf, "pratica0");
    job.setJarByClass(Pratica0.class);

    job.setMapperClass(MapperCreateCombinations.class); // MAP
    job.setReducerClass(ReduceCount.class);  // REDUCE
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    FileInputFormat.addInputPath(job, new Path(args[1]));
    FileOutputFormat.setOutputPath(job, new Path(args[2]));

    System.exit(job.waitForCompletion(true));
  }
}

