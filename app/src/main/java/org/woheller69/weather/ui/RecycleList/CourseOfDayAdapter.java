package org.woheller69.weather.ui.RecycleList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.woheller69.weather.R;
import org.woheller69.weather.database.CurrentWeatherData;
import org.woheller69.weather.database.Forecast;
import org.woheller69.weather.database.PFASQLiteHelper;
import org.woheller69.weather.ui.Help.StringFormatUtils;
import org.woheller69.weather.ui.UiResourceProvider;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

//**
// * Created by yonjuni on 02.01.17.
// * Adapter for the horizontal listView for course of the day.
// */import java.util.List;

public class CourseOfDayAdapter extends RecyclerView.Adapter<CourseOfDayAdapter.CourseOfDayViewHolder> {

    private List<Forecast> courseOfDayList;
    private Context context;
    private TextView recyclerViewHeader;
    private RecyclerView recyclerView;

    CourseOfDayAdapter(List<Forecast> courseOfDayList, Context context, TextView recyclerViewHeader, RecyclerView recyclerView) {
        this.context = context;
        this.courseOfDayList = courseOfDayList;
        this.recyclerViewHeader=recyclerViewHeader;
        this.recyclerView=recyclerView;
    }

    @Override
    public CourseOfDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course_of_day, parent, false);
        return new CourseOfDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseOfDayViewHolder holder, int position) {
        PFASQLiteHelper dbHelper = PFASQLiteHelper.getInstance(context);
        CurrentWeatherData currentWeather = dbHelper.getCurrentWeatherByCityId(courseOfDayList.get(position).getCity_id());

        Calendar forecastTime = Calendar.getInstance();
        forecastTime.setTimeZone(TimeZone.getTimeZone("GMT"));
        forecastTime.setTimeInMillis(courseOfDayList.get(position).getLocalForecastTime(context));

        Calendar sunSetTime = Calendar.getInstance();
        sunSetTime.setTimeZone(TimeZone.getTimeZone("GMT"));
        sunSetTime.setTimeInMillis(currentWeather.getTimeSunset() * 1000 + currentWeather.getTimeZoneSeconds() * 1000);
        sunSetTime.set(Calendar.DAY_OF_YEAR, forecastTime.get(Calendar.DAY_OF_YEAR));


        Calendar sunRiseTime = Calendar.getInstance();
        sunRiseTime.setTimeZone(TimeZone.getTimeZone("GMT"));
        sunRiseTime.setTimeInMillis(currentWeather.getTimeSunrise() * 1000 + currentWeather.getTimeZoneSeconds() * 1000);
        sunRiseTime.set(Calendar.DAY_OF_YEAR, forecastTime.get(Calendar.DAY_OF_YEAR));

        boolean isDay = forecastTime.after(sunRiseTime) && forecastTime.before(sunSetTime);

        holder.time.setText(StringFormatUtils.formatTimeWithoutZone(courseOfDayList.get(position).getLocalForecastTime(context)));

        updateRecyclerViewHeader();  //update header according to date in first visible item on the left

        setIcon(courseOfDayList.get(position).getWeatherID(), holder.weather, isDay);
        holder.humidity.setText(StringFormatUtils.formatInt(courseOfDayList.get(position).getHumidity(), "%rh"));
        holder.temperature.setText(StringFormatUtils.formatTemperature(context, courseOfDayList.get(position).getTemperature()));
        holder.wind_speed.setText(StringFormatUtils.formatWindSpeed(context, courseOfDayList.get(position).getWindSpeed()));
        holder.wind_direction.setText(StringFormatUtils.formatWindDir(context, courseOfDayList.get(position).getWindDirection()));

        if (courseOfDayList.get(position).getPrecipitation() == 0)
            holder.precipitation.setText("-");
        else
            holder.precipitation.setText(StringFormatUtils.formatDecimal(courseOfDayList.get(position).getPrecipitation(), "mm"));
    }

    //update header according to date in first visible item on the left of recyclerview
    private void updateRecyclerViewHeader() {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        LinearLayoutManager llm = (LinearLayoutManager) manager;
        assert llm != null;
        int visiblePosition = llm.findFirstVisibleItemPosition();
        if (visiblePosition>-1) {
            Calendar HeaderTime = Calendar.getInstance();
            HeaderTime.setTimeZone(TimeZone.getTimeZone("GMT"));
            HeaderTime.setTimeInMillis(courseOfDayList.get(visiblePosition).getLocalForecastTime(context));
            int headerday = HeaderTime.get(Calendar.DAY_OF_WEEK);
            headerday = StringFormatUtils.getDayLong(headerday);
            recyclerViewHeader.setText(context.getResources().getString(headerday));
        }
    }

    @Override
    public int getItemCount() {
        return courseOfDayList.size();
    }

    class CourseOfDayViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        ImageView weather;
        TextView temperature;
        TextView humidity;
        TextView precipitation;
        TextView wind_speed;
        TextView wind_direction;

        CourseOfDayViewHolder(View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.course_of_day_time);
            weather = itemView.findViewById(R.id.course_of_day_weather);
            temperature = itemView.findViewById(R.id.course_of_day_temperature);
            humidity = itemView.findViewById(R.id.course_of_day_humidity);
            precipitation = itemView.findViewById(R.id.course_of_day_precipitation);
            wind_speed = itemView.findViewById(R.id.course_of_day_wind_speed);
            wind_direction = itemView.findViewById(R.id.course_of_day_wind_direction);

        }
    }

    public void setIcon(int value, ImageView imageView, boolean isDay) {
        imageView.setImageResource(UiResourceProvider.getIconResourceForWeatherCategory(value, isDay));
    }
}

