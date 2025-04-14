with Ada.Text_IO;
with Ada.Integer_Text_IO;
with Ada.Text_IO.Unbounded_IO;

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

   -- State machine for reading lines from the event text file
   type Reading_State is (Date, Description, Category, Blank, Done);

   procedure Get_Events (File_Name : in String; 
                         Result : out Event_Vectors.Vector) is
      File : Ada.Text_IO.File_Type;
      State : Reading_State;
      Line, Date_String, Description_String, Category_String : 
         Ada.Strings.Unbounded.Unbounded_String;
      
      --  Package for printing out enumeration values
      package State_IO is
         new Ada.Text_IO.Enumeration_IO (Reading_State);
   begin
      Ada.Text_IO.Open (File, Mode => Ada.Text_IO.In_File, Name => File_Name);

      State := Date;
      loop
         Ada.Text_IO.Unbounded_IO.Get_Line (File, Line);
         if Ada.Text_IO.End_Of_File (File) then
            State := Done;
         end if;

         Ada.Text_IO.Put ("state = ");
         State_IO.Put (State);
         Ada.Text_IO.New_Line;

         case State is
            when Date =>
               Date_String := Line;
               State := Description;
            when Description =>
               Description_String := Line;
               State := Category;
            when Category =>
               Category_String := Line;
               State := Blank;
            when Blank =>
               -- make new event
               declare
                  Event : Event_Type;
               begin
                  Event := 
                     --  Cop-out! We can't parse ISO 8601 dates with the 
                     --  standard library (or can we?), so we'll fake it 
                     --  (and use the Util_Ada library later)
                     (Date => Ada.Calendar.Time_Of 
                        (Year => 2025, Month => 4, Day => 14), 
                      Description => Description_String,

                     -- Another cop-out! Not parsing the category string yet.
                      Category => (Primary => +"test", Secondary => +"test"));

                  Result.Append (Event);
               end;
               State := Date;
            when Done =>
               null;
         end case;
         exit when State = Done;
      end loop;

      Ada.Text_IO.Close (File);
   end Get_Events;

end Events;
