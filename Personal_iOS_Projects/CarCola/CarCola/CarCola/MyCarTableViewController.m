//
//  MyCarTableViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "MyCarTableViewController.h"

@interface MyCarTableViewController ()

@end

@implementation MyCarTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSData *data = [userDefaults objectForKey:@"savedCars"];
    self.carTable = [NSKeyedUnarchiver unarchiveObjectWithData:data];
    [userDefaults synchronize];

}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {

    return [self.carTable count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    MyCarTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"SavedCarCell" forIndexPath:indexPath];

    MyCar *thisCar = [self.carTable objectAtIndex:indexPath.row];
    cell.carMakeInCell.text = thisCar.carMake;
    cell.carModelInCell.text = thisCar.carModel;
    cell.carTrimInCell.text = thisCar.carTrim;
    cell.carYearInCell.text = thisCar.carYear;
    cell.carColorInCell.text = thisCar.carColor;
    cell.carNotesInCell.text = thisCar.carNotes;
    
//    cell.tag = arc4random();
    NSString *identifer = [NSString stringWithFormat:@"%@%@%@", thisCar.carModel,thisCar.carTrim,thisCar.carYear];
    NSInteger cellTag = indexPath.row;
    
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setInteger:cellTag forKey:identifer];
    [defaults synchronize];
    
    NSLog(@"the cell id is %@, tag number is %li, saved cell tag is %li", identifer, cellTag, [defaults integerForKey:identifer]);
    
    NSInteger rate = [cell getFromDefault];
    
    NSLog(@"the rate currently is %li", (long)rate);
    
    if(rate == 0){
        cell.rateStar.image = nil;
    }else if(rate == 1){
        cell.rateStar.image = [UIImage imageNamed:@"oneStar"];
    }else if (rate == 2){
        cell.rateStar.image = [UIImage imageNamed:@"twoStar"];
    }else if (rate == 3){
        cell.rateStar.image = [UIImage imageNamed:@"threeStar"];
    }else if (rate == 4){
        cell.rateStar.image = [UIImage imageNamed:@"fourStar"];
    }else{
        cell.rateStar.image = [UIImage imageNamed:@"fiveStar"];
    }
    

    return cell;
}




// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    return YES;
}


// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
//    [_carTable removeObject:[self.carTable objectAtIndex:indexPath.row]];

    if (editingStyle == UITableViewCellEditingStyleDelete) {

        [_carTable removeObject:[self.carTable objectAtIndex:indexPath.row]];

        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
        
        NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
        NSData *data = [NSKeyedArchiver archivedDataWithRootObject:self.carTable];
        [userDefaults setObject:data forKey:@"savedCars"];
        [userDefaults synchronize];
    }
 
}




- (IBAction)cancelButton:(id)sender {
        [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)editButton:(id)sender {
    
    if(self.tableView.editing == NO){
        [self.tableView setEditing:YES animated:YES];
        [sender setTitle: @"Done"];
    }
    else {
        [self.tableView setEditing:NO animated:YES];
        [sender setTitle: @"Edit"];
    }
    
}
@end
