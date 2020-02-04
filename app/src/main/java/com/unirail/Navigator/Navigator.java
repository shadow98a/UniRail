package com.unirail.Navigator;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.unirail.subway;

import java.util.Timer;
import java.util.TimerTask;

public final class Navigator extends Service
{
    static private final int run_period=10*1000;
    static private final int transfer_period=10*60*1000;

    private final IBinder binder=new binder();

    @Override
    public final IBinder onBind(final Intent intent)
    {
        return binder;
    }

    public final class binder extends Binder
    {
        public final Navigator get_Navigator()
        {
            return Navigator.this;
        }
    }

    public final void navigate(final argument_for_naviation[] arguments)
    {
        final Notifier Notifier=new Notifier(this);
        final Indicator Indicator=new Indicator(this);
        final Blocker Blocker=new Blocker(this);
        
        Notifier.notify_importantly("안내를 시작합니다. 잠시만 기다려주시기 바랍니다.");
        
        new Timer().schedule
        (
            new TimerTask()
            {
                private int index=0;

                private boolean is_tranferring=false;

                private argument_for_naviation argument=null;
                private String departure_station_statnNm;
                private String subwayNm;
                private int from_departure_station_to_arrival_station;
                private String arrival_station_statnNm;

                private subway.subline departure_station_subline;
                private String updnLine;

                private realtimeStationArrival departure_station_realtimeStationArrival;
                private String btrainNo;

                private stationInfo arrival_station_stationInfo;
                private realtimeStationPosition realtimeStationPosition;
                private boolean does_stop_at_arrival_station;

                private String previus_trainSttus;
                private int stoppage_duration;
                private boolean is_taken;

                private int from_current_station_to_arrival_station;

                private realtimeStationArrival arrival_station_realtimeStationArrival;

                private int transfer_duration;

                @Override
                public final void run()
                {
                    if(is_tranferring==false)
                    {
                        if(argument==null)
                        {
                            is_tranferring=false;

                            argument=arguments[index];
                            departure_station_statnNm=subway.statnNms.get(argument.get_fid());
                            subwayNm=argument.get_subwayNm();
                            from_departure_station_to_arrival_station=argument.get_from_departure_station_to_arrival_station();
                            arrival_station_statnNm=subway.statnNms.get(argument.get_tid());
                            departure_station_subline=subway.lines.get(subwayNm).get_departure_station_subline(departure_station_statnNm,from_departure_station_to_arrival_station,arrival_station_statnNm);
                            updnLine=departure_station_subline.get_updnLine();

                            departure_station_realtimeStationArrival=new realtimeStationArrival(departure_station_statnNm);
                            btrainNo=null;

                            arrival_station_stationInfo=new stationInfo(subway.get_legacy_statnNm(arrival_station_statnNm,subwayNm));
                            realtimeStationPosition=new realtimeStationPosition(subwayNm);

                            previus_trainSttus=null;
                            stoppage_duration=0;
                            is_taken=false;

                            from_current_station_to_arrival_station=from_departure_station_to_arrival_station;

                            arrival_station_realtimeStationArrival=new realtimeStationArrival(arrival_station_statnNm);

                            transfer_duration=transfer_period;
                        }
                        else
                        {
                            if(btrainNo==null)
                            {
                                Notifier.notify_unimportantly(arrival_station_statnNm+"에 서는 열차를 기다리고 있습니다.");

                                departure_station_realtimeStationArrival.request(subway.subwayIds.get(subwayNm),updnLine);//두 updnLine들에 대해 찾도록 고쳐야 한다.
                                if(departure_station_realtimeStationArrival.get_btrainNo()!=null&&!(departure_station_realtimeStationArrival.get_arvlCd().equals(realtimeStationArrival.is_approaching)||departure_station_realtimeStationArrival.get_arvlCd().equals(realtimeStationArrival.is_stopped)||departure_station_realtimeStationArrival.get_arvlCd().equals(realtimeStationArrival.is_leaving)))
                                {
                                    btrainNo=departure_station_realtimeStationArrival.get_btrainNo();

                                    realtimeStationPosition.request(btrainNo);
                                    final String train_directAt=realtimeStationPosition.get_directAt();
                                    arrival_station_stationInfo.request(subwayNm);
                                    final String arrival_station_directAt=arrival_station_stationInfo.get_directAt();
                                    does_stop_at_arrival_station=
                                        departure_station_subline.get_does_stop_at_destination_station(arrival_station_statnNm,departure_station_realtimeStationArrival.get_bstatnNm())&&
                                        (train_directAt.equals(com.unirail.Navigator.realtimeStationPosition.is_not_express_train)||updnLine.equals("상행")&&arrival_station_directAt.equals(stationInfo.express_train_stops_at_up_line)||updnLine.equals("하행")&&arrival_station_directAt.equals(stationInfo.express_train_stops_at_down_line)||arrival_station_directAt.equals(stationInfo.express_train_stops_at_all_line));
                                }
                            }
                            else
                            {
                                realtimeStationPosition.request(btrainNo);
                                if(realtimeStationPosition.get_statnNm()!=null)
                                {
                                    final String current_statnNm=realtimeStationPosition.get_statnNm();
                                    String current_trainSttus=realtimeStationPosition.get_trainSttus();

                                    if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                    {
                                        if(stoppage_duration<20*1000)
                                        {
                                            stoppage_duration+=run_period;
                                        }
                                        else
                                        {
                                            current_trainSttus=realtimeStationArrival.is_leaving;
                                        }
                                    }

                                    if(does_stop_at_arrival_station==false)
                                    {
                                        if(is_taken==false)
                                        {
                                            if(!current_statnNm.equals(departure_station_statnNm))
                                            {
                                                if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                {
                                                    if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                    {
                                                        stoppage_duration=0;
                                                    }
                                                }
                                                else
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                    {
                                                        if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                {
                                                    if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                    {
                                                        stoppage_duration=0;

                                                        Notifier.notify_approaching_train_does_not_stop_at_arrival_station("지금 들어오는 열차는, "+arrival_station_statnNm+"에 서지 않는 열차입니다.");
                                                        btrainNo=null;
                                                    }
                                                }
                                                else
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if(is_taken==false)
                                        {
                                            if(!current_statnNm.equals(departure_station_statnNm))
                                            {
                                                if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                {
                                                    if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                    {
                                                        stoppage_duration=0;
                                                    }

                                                    departure_station_realtimeStationArrival.request(btrainNo);
                                                    Notifier.notify_unimportantly(departure_station_realtimeStationArrival.get_arvlMsg2());
                                                }
                                                else
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                    {
                                                        if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }

                                                        departure_station_realtimeStationArrival.request(btrainNo);
                                                        Notifier.notify_unimportantly(departure_station_realtimeStationArrival.get_arvlMsg2());
                                                    }
                                                    else
                                                    {
                                                        if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }

                                                        departure_station_realtimeStationArrival.request(btrainNo);
                                                        Notifier.notify_unimportantly(departure_station_realtimeStationArrival.get_arvlMsg2());
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                {
                                                    if(previus_trainSttus==null||!current_trainSttus.equals(previus_trainSttus))
                                                    {
                                                        stoppage_duration=0;

                                                        Notifier.notify_approaching_train_does_stop_at_arrival_station("지금 "+arrival_station_statnNm+", "+arrival_station_statnNm+"에 서는 열차가 들어오고 있습니다. 내리는 사람이 모두 하차한 다음, 안전하게 승차하시기 바랍니다.");;
                                                        Indicator.indicate(0);//
                                                    }
                                                }
                                                else
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                            Indicator.stop();

                                                            is_taken=true;
                                                            notify_default(from_current_station_to_arrival_station,current_statnNm,current_trainSttus);

                                                            from_current_station_to_arrival_station--;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else
                                        {
                                            if(!current_statnNm.equals(arrival_station_statnNm))
                                            {
                                                if(from_current_station_to_arrival_station>1)
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                            stoppage_duration=0;

                                                            notify_default(from_current_station_to_arrival_station,current_statnNm,current_trainSttus);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                        {
                                                            if(!current_trainSttus.equals(previus_trainSttus))
                                                            {
                                                                notify_default(from_current_station_to_arrival_station,current_statnNm,current_trainSttus);
                                                                Blocker.block();
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(!current_trainSttus.equals(previus_trainSttus))
                                                            {
                                                                Blocker.stop();

                                                                notify_default(from_current_station_to_arrival_station,current_statnNm,current_trainSttus);

                                                                from_current_station_to_arrival_station--;
                                                            }
                                                        }
                                                    }
                                                }
                                                else
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                            stoppage_duration=0;

                                                            Notifier.notify_importantly("이번 역은 "+arrival_station_statnNm+"역의 전역인 "+current_statnNm+", "+current_statnNm+"역입니다. 내리실 준비를 하시기 바랍니다.");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                        {
                                                            if(!current_trainSttus.equals(previus_trainSttus))
                                                            {
                                                                notify_default(from_current_station_to_arrival_station,current_statnNm,current_trainSttus);
                                                                Blocker.block();
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(!current_trainSttus.equals(previus_trainSttus))
                                                            {
                                                                Blocker.stop();

                                                                notify_default(from_current_station_to_arrival_station,current_statnNm,current_trainSttus);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                if(current_trainSttus.equals(realtimeStationArrival.is_approaching))
                                                {
                                                    if(!current_trainSttus.equals(previus_trainSttus))
                                                    {
                                                        stoppage_duration=0;

                                                        arrival_station_realtimeStationArrival.request(btrainNo);
                                                        final String subwayHeading=arrival_station_realtimeStationArrival.get_subwayHeading();
                                                        Notifier.notify_importantly("이번 역은 "+current_statnNm+", "+current_statnNm+"역입니다. 내리실 문은 "+subwayHeading+"입니다.");
                                                        Indicator.indicate(0);//
                                                    }
                                                }
                                                else
                                                {
                                                    if(current_trainSttus.equals(realtimeStationArrival.is_stopped))
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(!current_trainSttus.equals(previus_trainSttus))
                                                        {
                                                            Indicator.stop();

                                                            argument=null;

                                                            if(index<arguments.length-1)
                                                            {
                                                                is_tranferring=true;
                                                            }
                                                            else
                                                            {
                                                                this.cancel();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    previus_trainSttus=current_trainSttus;
                                }
                            }
                        }
                    }
                    else
                    {
                        if(transfer_duration>0)
                        {
                            Notifier.notify_unimportantly(transfer_duration/1000/60+"분 "+transfer_duration/1000%60+"초 안에 "+subwayNm+"에서 "+arguments[index+1].get_subwayNm()+"으로 갈아타시기 바랍니다.");
                            transfer_duration-=run_period;
                        }
                        else
                        {
                            is_tranferring=false;
                            index++;
                        }
                    }
                }

                private final void notify_default(final int from_current_station_to_station, final String statnNm, final String trainSttus)
                {
                    String content_text="["+from_current_station_to_station+"]번째 전역 ("+statnNm+")";
                    if(trainSttus.equals(realtimeStationPosition.is_approaching))
                    {
                        content_text+=" 진입";
                    }
                    else
                    {
                        if(trainSttus.equals(realtimeStationPosition.is_stopped))
                        {
                            content_text+=" 도착";
                        }
                        else
                        {
                            content_text+=" 출발";
                        }
                    }

                    Notifier.notify_unimportantly(content_text);
                }
            },
            0,
            run_period
        );
    }

    static public final class argument_for_naviation
    {
        private String fid;
        private String subwayNm;
        private int from_departure_station_to_arrival_station;
        private String tid;

        public final void set_fid(final String fid)
        {
            this.fid=fid;
        }

        public final void set_subwayNm(final String subwayNm)
        {
            this.subwayNm=subwayNm;
        }

        public final void set_from_departure_station_to_arrival_station(final int from_departure_station_to_arrival_station)
        {
            this.from_departure_station_to_arrival_station=from_departure_station_to_arrival_station;
        }

        public final void set_tid(final String tid)
        {
            this.tid=tid;
        }

        public final String get_fid()
        {
            return fid;
        }

        public final String get_subwayNm()
        {
            return subwayNm;
        }

        public final int get_from_departure_station_to_arrival_station()
        {
            return from_departure_station_to_arrival_station;
        }

        public final String get_tid()
        {
            return tid;
        }
    }
}