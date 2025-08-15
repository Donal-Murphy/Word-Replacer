# Word Replacer 📝

A Java application that processes a text file and replaces its words with the **top 1000 most common words**, using **word embeddings** and **vector distance calculations** to find the closest matches.

---

## 📌 Features
- Reads an input text file and maps each word to a more common alternative.
- Supports multiple **vector similarity/distance algorithms**:
  - Cosine Similarity
  - Euclidean Distance
  - Dot Product
- Uses a preloaded list of the top 1000 most used words.
- Allows easy file selection and path validation.
- Outputs a simplified text file with replaced words.

---

## 🛠 Prerequisites
- **Java Development Kit (JDK)** – version 8 or newer.
- Command-line or IDE to run Java applications.

---

## 📂 Project Structure
```
Word-Replacer/
│
├── src/
│   ├── elegy.txt                 # Sample input file
│   ├── google-1000.txt           # List of top 1000 words
│   ├── out.txt                   # Output file (generated)
│   └── ie/atu/sw/                # Java source files
│       ├── Runner.java           # Main program entry point
│       ├── Menu.java             # Console-based menu
│       ├── FileManager.java      # Handles file reading/writing
│       ├── VectorMapperService.java
│       ├── TextFileSimplifier.java
│       ├── CosineCalculator.java
│       ├── EuclideanCalculator.java
│       ├── DotProductCalculator.java
│       └── ... (other helper classes)
├── README.md
```

---

## 🚀 How to Run

### 1️⃣ Compile the source code
From the `src` directory:
```bash
javac ie/atu/sw/*.java
```

### 2️⃣ Run the application
```bash
java ie.atu.sw.Runner
```

### 3️⃣ Follow the menu prompts
- Choose an input file (or use the provided `elegy.txt`).
- Choose the vector similarity/distance algorithm.
- Generate the simplified output file (`out.txt`).

---

## 📖 Example Workflow
**Input file (`elegy.txt`):**
```
The melancholy clouds drifted slowly across the darkened sky.
```

**Output file (`out.txt`):**
```
The sad clouds moved slowly across the dark sky.
```

---

## 👤 Author
**Dónal Murphy**  
- [LinkedIn](https://linkedin.com/in/donalmur)  
- [GitHub](https://github.com/Donal-Murphy)
