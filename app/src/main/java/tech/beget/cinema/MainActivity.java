package tech.beget.cinema;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movieArrayList;
    MovieAdapter adapter;

    //Создаем новый объект и вносим в него данные для приложения
    Movie[] movies = new Movie[] {
            new Movie("Чудо-женщина",
                    "Перед тем как стать Чудо-Женщиной, она была Дианой — принцессой амазонок, обученной быть непобедимой воительницей. И когда на берегах ограждённого от внешнего мира райского острова, который служил ей родиной, терпит крушение американский пилот и рассказывает о серьёзном конфликте, бушующем во внешнем мире, Диана покидает свой дом, чтобы справиться с этой угрозой. И там, сражаясь бок о бок с человеком в войне за мир, Диана обнаружит всю полноту своей власти… и своё истинное предназначение.",
                    "Пэтти Дженкинс",
                    2800000,
                    "https://im0-tub-ru.yandex.net/i?id=c9d5895c6a8e5a0707cdc3772dc68d9d-l&n=13",
                    "https://movietrailers.apple.com/movies/wb/wonderwoman/wonder-woman-tlr1_i320.m4v"),
            new Movie("Бэтмен против Супермена: На заре справедливости",
                    "Опасаясь, что действия богоподобного супергероя так и останутся бесконтрольными, грозный и могущественный страж Готэм Сити бросает вызов самому почитаемому в наши дни спасителю Метрополиса, в то время как весь остальной мир решает, какой герой ему по-настоящему нужен. И пока Бэтмен и Супермен пребывают в состоянии войны друг с другом, возникает новая угроза, которая ставит человечество под самую большую опасность, с которой оно когда-либо сталкивалось.",
                    "Зак Снайдер",
                    4884614,
                    "https://im0-tub-ru.yandex.net/i?id=3c9b066d32cdcfb7f9cb34030be09198-l&n=13",
                    "https://movietrailers.apple.com/movies/wb/batmanvsuperman/batmanvsuperman-tlr2_i320.m4v"),
            new Movie("Мстители 4: Финал",
                    "Оставшиеся в живых члены команды Мстителей и их союзники должны разработать новый план, который поможет противостоять разрушительным действиям могущественного титана Таноса. После наиболее масштабной и трагической битвы в истории они не могут допустить ошибку.",
                    "Энтони Руссо, Джо Руссо",
                    3105020,
                    "https://www.animatedtimes.com/wp-content/uploads/2018/08/avengers-4-time-travel-possible-AT.jpg",
                    "https://movietrailers.apple.com/movies/marvel/avengers-endgame/avengers-endgame-trailer-1_i320.m4v"),
            new Movie("Человек-паук: Через вселенные",
                    "Совершенно новый взгляд на вселенную Человека-паука от сценаристов и продюсеров Фила Лорда и Криса Миллера, подаривших зрителям анимационное приключение «Лего. фильм» и экшн-комедию «Мачо и ботан» . В центре сюжета уникальной и инновационной в визуальном плане картины подросток из Нью-Йорка Майлз Моралес, который живет в мире безграничных возможностей вселенных Человека-паука, где костюм супергероя носит не только он.",
                    "Боб Персичетти, Питер Рэмзи",
                    1765627,
                    "https://im0-tub-ru.yandex.net/i?id=d392621e156a372b6c54b2fb66d5d522-l&n=13",
                    "https://movietrailers.apple.com/movies/sony_pictures/spider-man-into-the-spider-verse/spider-man-into-the-spider-verse-trailer-1_i320.m4v"),
            new Movie("Хеллбой",
                    "Хеллбой отправляется в Англию на встречу с женой Мерлина – Кровавой королевой. От исхода этой битвы зависит судьба всего мира",
                    "Нил Маршалл",
                    940364,
                    "https://static.kinoafisha.info/k/movie_posters/1920x1080/upload/movie_posters/1/3/9/8329931/a839be38bd24dc0147cd1988b217c124.jpeg",
                    "https://movietrailers.apple.com/movies/lionsgate/hellboy/hellboy-trailer-2_i320.m4v")
    };

    //Связываем данные
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setIcon(R.drawable.ic_action_logo);
            actionBar.setTitle("    " + actionBar.getTitle());
        }

        movieArrayList = new ArrayList<>(Arrays.asList(movies));
        adapter = new MovieAdapter(this, movieArrayList);

        ListView movieListView = (ListView) findViewById(R.id.movieListView);
        movieListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        SearchView searchView = searchItem.getActionView() as SearchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Поиск", newText);

                movieArrayList.clear();

                if (newText.equals("")) {
                    movieArrayList.addAll(Arrays.asList(movies));
                } else {
                    for (Movie movie : movies) {
                        if (movie.getName().toLowerCase().contains(newText.toLowerCase())) {
                            movieArrayList.add(movie);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                return false;
            }
        });
            return super.onCreateOptionsMenu(menu);
    }

}
