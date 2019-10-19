select a.day, a.cms_id, a.city, a.times, a.times_rank
from day_video_city_access_topn_stat as a
         inner join (select day, city, sum(times) as times_sum
                     from day_video_city_access_topn_stat where city <> '全球'
                     group by day, city order by times_sum desc limit 0,5) as b
                    on b.city = a.city and b.day = a.day
order by b.times_sum desc
