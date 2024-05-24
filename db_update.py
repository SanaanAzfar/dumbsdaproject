import os
import mysql.connector
import wave

# Connect to MySQL database
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="capybaralord",
    database="Groovy"
)

# Create cursor
cursor = conn.cursor()

# Function to insert song data into database
def insert_song(name, artist, genre, timeDuration, releaseDate, lyrics, plays, filePath):
    sql = "INSERT INTO Song (name, artist, genre, timeDuration, releaseDate, lyrics, plays, filePath) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
    val = (name, artist, genre, timeDuration, releaseDate, lyrics, plays, filePath)
    cursor.execute(sql, val)
    conn.commit()

# Path to directory containing music files
music_directory = "/home/doc/Projects/Groovy/music/"

random_artist = "Devin the Dude"
random_genre = "Hip Hop"
random_release_date = "2022-01-01"  
for filename in os.listdir(music_directory):
    if filename.endswith(".wav"): 
        try:
            with wave.open(os.path.join(music_directory, filename), 'rb') as wav_file:
                name = os.path.splitext(filename)[0] 
                artist = random_artist
                genre = random_genre
                timeDuration = str(round(wav_file.getnframes() / wav_file.getframerate())) + " seconds"  
                releaseDate = random_release_date
                lyrics = "No lyrics available" 
                plays = 0 
                filePath = os.path.join(music_directory, filename)

                insert_song(name, artist, genre, timeDuration, releaseDate, lyrics, plays, filePath)
                print(f"Song '{name}' added to database.")
        except Exception as e:
            print(f"Error processing file '{filename}': {e}")

cursor.close()
conn.close()
