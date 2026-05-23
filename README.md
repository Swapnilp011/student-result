BDM 7 to 9 https://colab.research.google.com/drive/1ptBMctdVtXK-kiGrqyQgI0DMbX8Dm-kf?usp=sharing



Aim
To write a MapReduce program for counting words from a given text file.


import java.util.*;

public class WordCount {
    public static void main(String args[]) {

        String text = "hello hadoop hello bigdata";

        String words[] = text.split(" ");

        HashMap<String, Integer> map = new HashMap<>();

        for(String word : words) {

            if(map.containsKey(word))
                map.put(word, map.get(word) + 1);
            else
                map.put(word, 1);
        }

        System.out.println(map);
    }
}


Output
{hello=2, hadoop=1, bigdata=1}



2. Simple Machine Learning Program
Aim
To analyze real-world data using Machine Learning techniques.


from sklearn.linear_model import LinearRegression

x = [[1],[2],[3],[4]]
y = [2,4,6,8]

model = LinearRegression()

model.fit(x,y)

print(model.predict([[5]]))


Output
[10.]