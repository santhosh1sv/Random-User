package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.data.HabitModel
import com.compose.jetapp.data.habitList

@Preview(showBackground = true)
@Composable
fun Habit(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(top = 40.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize()
    ) {
        Text("Choose habit", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier.height(10.dp))
        Text(
            "Choose your daily habits, you can choose more than one",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier.height(20.dp))
        HabitList()
        Spacer(modifier.height(30.dp))
        Button(
            modifier = modifier.fillMaxWidth(), onClick = {

            }, shape = RoundedCornerShape(5.dp), colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            ), contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text("Get Started!", fontSize = 20.sp)
        }
    }
}

@Composable
fun ColumnScope.HabitList(modifier: Modifier = Modifier) {
    val habitItemList = remember { mutableStateListOf<HabitModel>() }
    habitItemList.addAll(habitList)
    LazyVerticalGrid(
        modifier = modifier.weight(1.0f),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(habitItemList.size) {
            HabitItem(model = habitItemList[it]) {
                habitItemList[it] = habitItemList[it].copy(
                    color = Color(0xfffdf8f5), borderColor = Color(0xfffd5b32)
                )
            }
        }

    }

}

@Composable
fun HabitItem(modifier: Modifier = Modifier, model: HabitModel, onClick: () -> Unit) {
    Column(
        modifier
            .height(105.dp)
            .background(
                color = model.color
            )
            .border(width = 1.dp, shape = RoundedCornerShape(15.dp), color = model.borderColor)
            .clip(RoundedCornerShape(15.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true
                )
            ) { onClick() }
            .padding(top = 15.dp, bottom = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = modifier.size(40.dp),
            painter = painterResource(model.drawable),
            contentDescription = "",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier.height(15.dp))
        Text(model.name, fontSize = 18.sp)
    }
}
/*
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'dart:ui';

class HabitModel {
  final String name;
  final String imageUrl;
  bool isSelected;
  HabitModel(this.name, this.imageUrl, {this.isSelected = false});
}

class HabitDayModel {
  final String day;
  final String date;
  bool isSelected;
  HabitDayModel(this.day, this.date, {this.isSelected = false});
}

class HabitOfDayModel {
  final String name;
  final String detail;
  final int color;
  final String imageUrl;

  final bool isComplete;
  const HabitOfDayModel(
      {required this.name,
      required this.detail,
      required this.color,
      required this.imageUrl,
      required this.isComplete});
}

class HabitOfDayListNotifier extends Notifier<List<HabitOfDayModel>> {
  final habitOfDayList = [
    HabitOfDayModel(
        name: 'Bicycle',
        detail: '07.00 for 10km',
        color: 0xffeaf5ed,
        imageUrl: 'https://cdn-icons-png.flaticon.com/128/2362/2362665.png',
        isComplete: true),
    HabitOfDayModel(
        name: 'Running',
        detail: '12.00 for 5km',
        color: 0xfffaede6,
        imageUrl: 'https://cdn-icons-png.flaticon.com/128/12915/12915771.png',
        isComplete: true),
    HabitOfDayModel(
        name: 'Gym',
        detail: '100 for 1hr',
        color: 0xfff9f9e4,
        imageUrl: 'https://cdn-icons-png.flaticon.com/128/2112/2112184.png',
        isComplete: true),
    HabitOfDayModel(
        name: 'Reading',
        detail: '3 for 1hr',
        color: 0xfff4e9f2,
        imageUrl: 'https://cdn-icons-png.flaticon.com/128/4072/4072217.png',
        isComplete: false)
  ];

  @override
  List<HabitOfDayModel> build() {
    return habitOfDayList;
  }
}

class HabitDayListNotifier extends AutoDisposeNotifier<List<HabitDayModel>> {
  final habitDayList = [
    HabitDayModel('Tue', '06', isSelected: true),
    HabitDayModel('Wed', '07'),
    HabitDayModel('Thu', '08'),
    HabitDayModel('Fri', '09'),
    HabitDayModel('Sat', '10'),
    HabitDayModel('Sun', '11'),
    HabitDayModel('Mon', '12')
  ];

  @override
  List<HabitDayModel> build() {
    return habitDayList;
  }

  void notifyDataSetChanged() {
    state = [...state];
  }
}

class HabitListNotifier extends Notifier<List<HabitModel>> {
  final habitList = [
    HabitModel('Work Out',
        'https://cdn-icons-png.flaticon.com/128/12915/12915771.png'),
    HabitModel(
        'Eat Food', 'https://cdn-icons-png.flaticon.com/128/878/878052.png'),
    HabitModel(
        'Music', 'https://cdn-icons-png.flaticon.com/128/12422/12422447.png'),
    HabitModel('Art & Design',
        'https://cdn-icons-png.flaticon.com/128/881/881296.png'),
    HabitModel('Travelling',
        'https://cdn-icons-png.flaticon.com/128/2060/2060284.png'),
    HabitModel(
        'Read Book', 'https://cdn-icons-png.flaticon.com/128/4072/4072217.png'),
    HabitModel(
        'Gaming', 'https://cdn-icons-png.flaticon.com/128/808/808439.png'),
    HabitModel(
        'Mechanic', 'https://cdn-icons-png.flaticon.com/128/2593/2593065.png')
  ];

  @override
  List<HabitModel> build() {
    return habitList;
  }

  void notifyDataSetChanged() {
    state = [...state];
  }
}

void main() {
  runApp(ProviderScope(child: HabitTrackerApp()));
}

class HabitTrackerApp extends StatelessWidget {
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        scrollBehavior: MaterialScrollBehavior().copyWith(
          dragDevices: {
            PointerDeviceKind.mouse,
            PointerDeviceKind.touch,
            PointerDeviceKind.stylus,
            PointerDeviceKind.unknown
          },
        ),
        theme: ThemeData(
            primaryColor: Color(0xfffd5b32),
            colorScheme: ColorScheme.fromSeed(seedColor: Color(0xfffd5b32))),
        home: GetStarted());
  }
}

class HomeDetail extends StatelessWidget {
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Theme.of(context).colorScheme.onPrimary,
        body: Padding(
            padding: EdgeInsets.fromLTRB(15, 30, 15, 30),
            child:
                Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
              Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
                CustomIconButton(Icons.now_widgets_outlined, () {
                  Navigator.of(context).pop();
                }),
                Text('Calorie stats',
                    style: TextStyle(
                        fontSize: 12,
                        color: Theme.of(context).colorScheme.shadow,
                        fontWeight: FontWeight.bold)),
                CustomIconButton(Icons.calendar_month, () {}),
              ]),
              SizedBox(height: 20),
              Analytics(),
              SizedBox(height: 20),
              Header('Challenge', 'See all')
            ])));
  }
}

class Analytics extends ConsumerWidget {
  Widget build(BuildContext context, WidgetRef ref) {
    return Stack(children: [
      Container(
        width: double.maxFinite,
        padding: EdgeInsets.all(15),
        decoration: BoxDecoration(
            color: Color(0xfffdfdfd),
            borderRadius: BorderRadius.circular(10),
            border: Border.all(color: Color(0xfff4f4f4))),
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Text('Analytics',
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.w600)),
          SizedBox(height: 3),
          Text('7,830 Calls',
              style: TextStyle(
                  fontSize: 10,
                  color: Theme.of(context).primaryColor,
                  fontWeight: FontWeight.w600)),
          SizedBox(height: 40),
          Graph()
        ]),
      ),
      Positioned(
          top: 35,
          right: 10,
          child: Container(
              padding: EdgeInsets.all(8),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(5),
                  color: Theme.of(context).colorScheme.onPrimary),
              child: Row(children: [
                Image.network(
                    'https://cdn-icons-png.flaticon.com/128/785/785116.png',
                    width: 15,
                    height: 15,
                    fit: BoxFit.fill),
                const SizedBox(width: 10),
                NameAndDetail('Burn', '535 Calls')
              ])))
    ]);
  }
}

class Graph extends StatelessWidget {
  Widget build(BuildContext context) {
    return Stack(children: [
      Column(children: [
        DashedLine(),
        SizedBox(height: 15),
        DashedLine(),
        SizedBox(height: 15),
        DashedLine(),
        SizedBox(height: 15),
        DashedLine(),
        SizedBox(height: 15),
        DashedLine(),
        SizedBox(height: 15),
        DashedLine()
      ]),
      //todo
      Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [])
    ]);
  }
}

class DashedLine extends StatelessWidget {
  final lineWidth = 6.0;
  final spaceWidth = 3.0;
  Widget build(BuildContext context) {
    return LayoutBuilder(builder: (ctx, constraints) {
      return Row(children: [
        ...List.generate(
            (constraints.maxWidth / (lineWidth + spaceWidth)).toInt(),
            (index) => Row(children: [
                  Container(
                      width: lineWidth,
                      height: 2,
                      decoration: BoxDecoration(color: Color(0xfff4f4f4))),
                  SizedBox(width: spaceWidth)
                ]))
      ]);
    });
  }
}

class Home extends ConsumerWidget {
  Widget build(BuildContext context, WidgetRef ref) {
    final habitDayList = ref.watch(habitDayListProvider);
    final habitDay =
        habitDayList.where((item) => item.isSelected == true).toList()[0].day;
    return Scaffold(
        backgroundColor: Theme.of(context).colorScheme.onPrimary,
        body: Padding(
            padding: EdgeInsets.fromLTRB(15, 30, 15, 30),
            child: Column(children: [
              Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
                CustomIconButton(Icons.now_widgets_outlined, () {
                  Navigator.of(context).pop();
                }),
                Text('Wednesday,24',
                    style: TextStyle(
                        fontSize: 12,
                        color: Theme.of(context).colorScheme.outline)),
                CustomIconButton(Icons.calendar_month, () {}),
              ]),
              const SizedBox(height: 20),
              Container(
                  padding: EdgeInsets.all(10),
                  decoration: BoxDecoration(
                      color: Theme.of(context).colorScheme.shadow,
                      borderRadius: BorderRadius.circular(10)),
                  child: Stack(children: [
                    Align(
                        alignment: Alignment.topRight,
                        child: Icon(Icons.info_outline,
                            size: 15,
                            color: Theme.of(context).colorScheme.onPrimary)),
                    Padding(
                        padding: EdgeInsets.all(5),
                        child: Row(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              CircleAvatar(
                                  radius: 25,
                                  backgroundColor: Theme.of(context)
                                      .colorScheme
                                      .onPrimary
                                      .withOpacity(0.3),
                                  child: Padding(
                                      padding: EdgeInsets.all(10),
                                      child: Image.network(
                                          'https://cdn-icons-png.flaticon.com/128/4072/4072217.png',
                                          fit: BoxFit.fill))),
                              const SizedBox(width: 10),
                              Expanded(
                                  child: Column(
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                    Text('Notification!',
                                        style: TextStyle(
                                            fontSize: 12,
                                            color: Theme.of(context)
                                                .colorScheme
                                                .onPrimary)),
                                    SizedBox(height: 5),
                                    Text(
                                        'Now is the time to read the book, you can change it in settings.',
                                        style: TextStyle(
                                            fontSize: 10,
                                            color: Color(0xff7f7f7f)))
                                  ]))
                            ]))
                  ])),
              const SizedBox(height: 20),
              DayList(habitDayList),
              const SizedBox(height: 20),
              Header(title(habitDay), 'See all'),
              const SizedBox(height: 10),
              Expanded(child: HabitOfDayList())
            ])));
  }

  String title(String habitDay) {
    switch (habitDay) {
      case 'Mon':
        return 'Monday habit';

      case 'Tue':
        return 'Tuesday habit';

      case 'Wed':
        return 'Wednesday habit';

      case 'Thu':
        return 'Thursday habit';

      case 'Fri':
        return 'Friday habit';

      case 'Sat':
        return 'Saturday habit';

      case 'Sun':
        return 'Sunday habit';

      default:
        return 'Monday habit';
    }
  }
}

class Header extends StatelessWidget {
  final String text;
  final String button;
  Header(this.text, this.button);

  Widget build(BuildContext context) {
    return Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
      Text(text,
          style: TextStyle(
              fontSize: 14,
              color: Theme.of(context).colorScheme.shadow,
              fontWeight: FontWeight.bold)),
      Text(button,
          style: TextStyle(
              fontSize: 10, color: Theme.of(context).colorScheme.outline)),
    ]);
  }
}

final habitOfDayListProvider =
    NotifierProvider<HabitOfDayListNotifier, List<HabitOfDayModel>>(() {
  return HabitOfDayListNotifier();
});
// final stringProvider = Provider<String>((ref){
//   return 'hi raja';
// });

class HabitOfDayList extends ConsumerWidget {
  Widget build(BuildContext context, WidgetRef ref) {
    final habitOfDayList = ref.watch(habitOfDayListProvider);
    return GridView.builder(
        itemCount: habitOfDayList.length,
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2, mainAxisSpacing: 10, crossAxisSpacing: 10),
        itemBuilder: (context, index) => HabitOfDayItem(habitOfDayList[index]));
  }
}

class HabitOfDayItem extends StatelessWidget {
  final HabitOfDayModel habitOfDayModel;
  HabitOfDayItem(this.habitOfDayModel);
  Widget build(BuildContext context) {
    return GestureDetector(
        onTap: () {
          Navigator.of(context)
              .push(MaterialPageRoute(builder: (context) => HomeDetail()));
        },
        child: Container(
            padding: EdgeInsets.all(10),
            decoration: BoxDecoration(
                color: Color(habitOfDayModel.color),
                borderRadius: BorderRadius.circular(10)),
            child: Stack(children: [
              Align(
                  alignment: Alignment.topRight,
                  child: CircleAvatar(
                      radius: 10,
                      backgroundColor: Theme.of(context).colorScheme.onPrimary,
                      child: Icon(Icons.check,
                          size: 15,
                          color: habitOfDayModel.isComplete
                              ? Theme.of(context).primaryColor
                              : Color(0xffe1e5f0)))),
              Padding(
                  padding: EdgeInsets.all(5),
                  child: Image.network(habitOfDayModel.imageUrl,
                      width: 40, height: 40, fit: BoxFit.fill)),
              Positioned(
                  left: 0,
                  right: 0,
                  bottom: 0,
                  child: NameAndDetail(
                      habitOfDayModel.name, habitOfDayModel.detail))
            ])));
  }
}

class NameAndDetail extends StatelessWidget {
  final String name;
  final String detail;
  NameAndDetail(this.name, this.detail);

  Widget build(BuildContext context) {
    return Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
      Text(name,
          style: TextStyle(
              fontSize: 12,
              color: Theme.of(context).colorScheme.shadow,
              fontWeight: FontWeight.w600)),
      Text(detail,
          style: TextStyle(
              fontSize: 10, color: Theme.of(context).colorScheme.outline))
    ]);
  }
}

final habitDayListProvider =
    NotifierProvider.autoDispose<HabitDayListNotifier, List<HabitDayModel>>(() {
  return HabitDayListNotifier();
});

class DayList extends ConsumerStatefulWidget {
  final List<HabitDayModel> habitDayList;
  DayList(this.habitDayList);
  ConsumerState<DayList> createState() => DayListState();
}

class DayListState extends ConsumerState<DayList> {
  int lastIndex = 0;

  Widget build(BuildContext context) {
    return SizedBox(
        height: 60,
        child: ListView.separated(
            scrollDirection: Axis.horizontal,
            itemCount: widget.habitDayList.length,
            separatorBuilder: (context, index) => SizedBox(width: 10),
            itemBuilder: (context, index) {
              return GestureDetector(
                  onTap: () {
                    widget.habitDayList[lastIndex].isSelected = false;
                    widget.habitDayList[index].isSelected = true;
                    lastIndex = index;
                    ref
                        .read(habitDayListProvider.notifier)
                        .notifyDataSetChanged();
                  },
                  child: Container(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      decoration: BoxDecoration(
                          color: widget.habitDayList[index].isSelected
                              ? Theme.of(context).primaryColor
                              : Theme.of(context).colorScheme.onPrimary,
                          border: Border.all(color: Color(0xfff4f4f4)),
                          borderRadius: BorderRadius.circular(10)),
                      child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(widget.habitDayList[index].day,
                                style: TextStyle(
                                    fontSize: 10,
                                    color: widget.habitDayList[index].isSelected
                                        ? Theme.of(context)
                                            .colorScheme
                                            .onPrimary
                                        : Theme.of(context)
                                            .colorScheme
                                            .outline)),
                            const SizedBox(height: 5),
                            Text(widget.habitDayList[index].date,
                                style: TextStyle(
                                    fontSize: 12,
                                    color: widget.habitDayList[index].isSelected
                                        ? Theme.of(context)
                                            .colorScheme
                                            .onPrimary
                                        : Theme.of(context).colorScheme.outline,
                                    fontWeight: FontWeight.w600))
                          ])));
            }));
  }
}

class CustomIconButton extends StatelessWidget {
  final IconData icon;
  final void Function() onPressed;

  const CustomIconButton(this.icon, this.onPressed);

  Widget build(BuildContext context) {
    return IconButton(
        icon: Icon(icon, size: 15, color: Theme.of(context).colorScheme.shadow),
        constraints: BoxConstraints(),
        padding: EdgeInsets.all(10),
        style: IconButton.styleFrom(side: BorderSide(color: Color(0xfff4f4f4))),
        onPressed: onPressed);
  }
}

class GetStarted extends StatelessWidget {
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Theme.of(context).colorScheme.onPrimary,
        body: SingleChildScrollView(
            padding: EdgeInsets.fromLTRB(15, 30, 15, 30),
            child:
                Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
              Text('Choose habit',
                  style: TextStyle(
                      color: Theme.of(context).colorScheme.shadow,
                      fontSize: 20,
                      fontWeight: FontWeight.bold)),
              SizedBox(height: 5),
              Text('Choose your daily habits, you can choose more than one',
                  style: TextStyle(
                      color: Theme.of(context).colorScheme.outline,
                      fontSize: 10)),
              SizedBox(height: 20),
              GetStartedBottom()
            ])));
  }
}

final habitListProvider =
    NotifierProvider<HabitListNotifier, List<HabitModel>>(() {
  return HabitListNotifier();
});

class GetStartedBottom extends ConsumerWidget {
  Widget build(BuildContext context, WidgetRef ref) {
    final habitList = ref.watch(habitListProvider);
    return Column(children: [
      HabitList(habitList),
      const SizedBox(height: 30),
      SizedBox(
          height: 40,
          width: double.maxFinite,
          child: ElevatedButton(
              style: ElevatedButton.styleFrom(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(5)),
                  backgroundColor: Theme.of(context).colorScheme.shadow,
                  foregroundColor: Theme.of(context).colorScheme.onPrimary),
              onPressed: () {
                if (habitList.any((item) => item.isSelected == true)) {
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (context) => Home()));
                } else {
                  final snackBar = SnackBar(
                      backgroundColor: Theme.of(context).colorScheme.shadow,
                      content: Text('Please select at least one item'),
                      duration: Duration(milliseconds: 1500));
                  ScaffoldMessenger.of(context).showSnackBar(snackBar);
                }
              },
              child: const Text('Get Started!',
                  style: TextStyle(fontSize: 12, fontWeight: FontWeight.w600))))
    ]);
  }
}

class HabitList extends ConsumerWidget {
  final List<HabitModel> habitList;
  HabitList(this.habitList);
  Widget build(BuildContext context, WidgetRef ref) {
    return GridView.builder(
        itemCount: habitList.length,
        shrinkWrap: true,
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            mainAxisSpacing: 16,
            crossAxisSpacing: 8,
            mainAxisExtent: 80),
        itemBuilder: (context, index) => HabitListItem(habitList[index], () {
              habitList[index].isSelected = true;
              ref.read(habitListProvider.notifier).notifyDataSetChanged();
            }));
  }
}

class HabitListItem extends StatelessWidget {
  final HabitModel habitModel;
  final void Function() onTap;

  const HabitListItem(this.habitModel, this.onTap);

  Widget build(BuildContext context) {
    return InkWell(
        onTap: onTap,
        borderRadius: BorderRadius.circular(10),
        child: Ink(
            decoration: BoxDecoration(
                color: habitModel.isSelected
                    ? Color(0xfffdf8f5)
                    : Theme.of(context).colorScheme.onPrimary,
                border: Border.all(
                    color: habitModel.isSelected
                        ? Theme.of(context).primaryColor
                        : Color(0xfff4f4f4)),
                borderRadius: BorderRadius.circular(10)),
            child: Center(
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                  Image.network(habitModel.imageUrl,
                      width: 35, height: 35, fit: BoxFit.fill),
                  SizedBox(height: 7),
                  Text(habitModel.name,
                      style: TextStyle(
                          fontSize: 12,
                          fontWeight: FontWeight.w600,
                          color: Theme.of(context).colorScheme.shadow))
                ]))));
  }
}
*/