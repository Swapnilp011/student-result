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
# Input values
x = [[10],[20],[30],[40],[50]]

# Output values
y = [100,200,300,400,500]

# Create model
model = LinearRegression()

# Train model
model.fit(x, y)

# Predict value
print("Prediction for 60 =", model.predict([[60]]))