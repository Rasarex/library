package unislask;

import java.util.Map;
import java.util.Vector;
import java.io.BufferedWriter;
import java.io.IOException;


class Data{

    Data(BufferedWriter writer_){
        writer = writer_;
    }
    void update(){
        try{
            for(Author author : authors){
                writer.write("BEGIN AUTHOR\n");
                for(Map.Entry<String,String> attribute : author.attributes.entrySet()){
                    writer.write(attribute.getKey() + ": " + attribute.getValue());
                }
                writer.write("END AUTHOR\n");
            }
            for(Book book : books){
                writer.write("BEGIN BOOKETQ\n");
                for(Map.Entry<String,String> attribute : book.attributes.entrySet()){
                    writer.write(attribute.getKey() + ": " + attribute.getValue());
                }
                writer.write("END BOOKETQ\n");
            }
           
        }
        catch(IOException e){

        }
    }

    BufferedWriter writer;
    Vector<Book> books;
    Vector<Author> authors;
    Vector<BookSeries> series;
}