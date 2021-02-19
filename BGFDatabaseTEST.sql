Drop Database If Exists BGFdDataTEST;

Create Database BGFdDataTEST;

Use BGFdDataTEST;



Create Table Trainer (
    UserID int primary key auto_increment not null,
    TrainerName varchar(60) not null,
    TrainerPassword varchar(450) null
);

Create table Team (
TeamID int primary key not null auto_increment,
TeamName varchar(60),
UserID int,
	foreign key Trainer_UserID( UserId)
		references Trainer (UserId)
);
SELECT * 
FROM Team;


Create Table TypeStat(
TypeStatID int primary key auto_increment not null,
Normal int,
Fire Int,
Water int,
Grass int,
Electric int,
Ice int,
Fighting int,
Poison int,
Ground int, 
Flying int,
Psychic int,
Bug int, 
Rock int,
Ghost int,
Dark int,
Dragon int,
Steel int,
Fairy int, 
TeamID int,
constraint fk_TSTeamId foreign key (TeamID)
  references Team(TeamID)
);

Create table BaseStat (
BaseStatID int primary key auto_increment not null,
HP int,
Attack int,
Defense int,
SpAtk int,
SpDef int,
Speed int,
TeamID int,
constraint fk_BSTeamId foreign key (TeamID)
  references Team(TeamID)
);

Create table PokemonInTeam(
  PokemonTeamId int primary key auto_increment not null,
  Pokemon1 int,
  Pokemon2 int,
  Pokemon3 int,
  Pokemon4 int,
  Pokemon5 int,
  Pokemon6 int,
  TeamID int,
  constraint fk_PITTeamId foreign key (TeamID)
  references Team(TeamID)
);
