with Ada.Text_IO;
with Ada.Integer_Text_IO;

package body Events is
   --  Instantiate a generic package for sorting our vectors.
   package Event_Vectors_Sorting is new
      Event_Vectors.Generic_Sorting;

   --  Convenience function to make unbounded strings.
   function "+" (S : String) return Unbounded_String
      renames Ada.Strings.Unbounded.To_Unbounded_String;

   --  Comparison function for Event_Type for sorting
   function "<" (Left, Right : Event_Type) return Boolean is
      use Ada.Calendar;
   begin
      if Year (Left.Date) /= Year (Right.Date) then
         return Year (Left.Date) < Year (Right.Date);
      elsif Month (Left.Date) /= Month (Right.Date) then
         return Month (Left.Date) < Month (Right.Date);
      else
         return Day (Left.Date) < Day (Right.Date);
      end if;
   end "<";

   --  Prints a date
   procedure Put (Date : Ada.Calendar.Time) is
      use Ada.Calendar;
      Year : Year_Number;
      Month : Month_Number;
      Day : Day_Number;
      Seconds_Ignored : Day_Duration;
   begin
      Split (Date, Year, Month, Day, Seconds_Ignored);

      Ada.Integer_Text_IO.Put (Year, Width => 4);
      Ada.Text_IO.Put (" ");
      Ada.Integer_Text_IO.Put (Month, Width => 2);
      Ada.Text_IO.Put (" ");
      Ada.Integer_Text_IO.Put (Day, Width => 2);
   end Put;

   -- Prints an event.
   procedure Put (Event : Event_Type) is
   begin
      Put (Event.Date);
      Ada.Text_IO.Put (" : ");
      Ada.Text_IO.Put (Ada.Strings.Unbounded.To_String (Event.Description));
      Ada.Text_IO.New_Line;
   end Put;

   procedure Sort (EV : in out Event_Vectors.Vector) is
   begin
      Event_Vectors_Sorting.Sort (EV);
   end Sort;

begin
   --  Use Ada 2022 container aggregate to initialize test events
   
   Test_Events := [((Date => (Ada.Calendar.Time_Of (Year => 1940, Month => 3, Day => 25)),
                   Description => +"Jean Ichbiah, French computer scientist and chief designer of Ada, is born",
                   Category => (+"computing", +"people"))),
   ((Date => (Ada.Calendar.Time_Of (Year => 1934, Month => 2, Day => 15)), 
                   Description => +"Niklaus Wirth, Swiss computer scientist and designer of Pascal, is born", 
                   Category => (+"computing", +"people"))),
   ((Date => (Ada.Calendar.Time_Of (Year => 1934, Month => 1, Day => 11)),
                   Description => +"Sir Charles Anthony Hoare, British computer scientist, is born",
                   Category => (Primary => +"computing", Secondary => +"people"))),
   ((Date => (Ada.Calendar.Time_Of (Year => 1938, Month => 1, Day => 10)),
                   Description => +"Donald Knuth, American computer scientist, is born",
                   Category => (Primary => +"computing", Secondary => +"people")))];
end Events;
